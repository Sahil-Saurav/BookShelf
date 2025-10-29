package com.example.bookshelf.Data.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Upsert
    suspend fun upsertBook(bookEntity: List<BookEntity>)

    @Query("select * from book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

    @Query("select COUNT(*) from book where finishedReading = 1")
    fun getCountOfFinishedBookCount(): Flow<Int>

    @Query("select COUNT(*) from book where currentlyReading = 1")
    fun getCountOfReadingBookCount(): Flow<Int>

    @Query("select COUNT(*) from book where currentlyReading = 0 AND finishedReading = 0")
    fun getCountOfNotStartedBook(): Flow<Int>

    @Query("select * from book where finishedReading = 1")
    fun getFinishedBook(): Flow<List<BookEntity>>

    @Query("select * from book where currentlyReading = 1")
    fun getCurrentlyReadingBook(): Flow<List<BookEntity>>

    @Query("select * from book where finishedReading = 0 AND currentlyReading = 0")
    fun getNotStartedBook(): Flow<List<BookEntity>>

    @Query("select COUNT(*) > 0 from book where id = :bookId")
    fun doesBookExist(bookId: String): Flow<Boolean>
}