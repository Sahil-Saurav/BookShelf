package com.example.bookshelf.Data.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Upsert
    suspend fun upsertBook(bookEntity: BookEntity)

    @Query("select * from book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)
}