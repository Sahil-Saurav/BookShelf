package com.example.bookshelf.Domain.Repository

import com.example.bookshelf.Data.Local.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookDataBaseRepository {
    suspend fun addBook(bookEntity: List<BookEntity>)
    fun getAllBooks():Flow<List<BookEntity>>
    suspend fun deleteBook(bookEntity: BookEntity)
    fun getCountOfFinishedBook(): Flow<Int>
    fun getCountOfCurrentlyReadingBook(): Flow<Int>
    fun getCountOfNotReadingBook(): Flow<Int>
    fun getFinishedBook(): Flow<List<BookEntity>>
    fun getCurrentlyReadingBook(): Flow<List<BookEntity>>
    fun getNotReadingBook(): Flow<List<BookEntity>>
    fun doesBookExist(bookID: String): Flow<Boolean>
}