package com.example.bookshelf.Domain.Repository

import com.example.bookshelf.Data.Local.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookDataBaseRepository {
    suspend fun addBook(bookEntity: BookEntity)
    suspend fun updateBook(bookEntity: BookEntity)
    fun getAllBooks():Flow<List<BookEntity>>
    suspend fun deleteBook(bookEntity: BookEntity)
}