package com.example.bookshelf.Domain.Repository

import com.example.bookshelf.Data.Local.BookEntity
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {
    suspend fun addUser(uid:String)
    suspend fun addBook(uid: String,bookEntity: BookEntity)
    suspend fun deleteBook(uid: String,bookEntity: BookEntity)
    suspend fun getBook(uid: String):Flow<List<BookEntity>>
}