package com.example.bookshelf.Domain.Repository


import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.BookByBookNameAndAuthorDto
import com.example.bookshelf.Data.Remote.SearchByNameDTO.BookByBookNameDto

interface BookShelfRepository {
    suspend fun getBookByName(bookName:String,apiKey: String): BookByBookNameDto
    suspend fun getBookByNameAndAuthor(bookName:String,authorName:String,apiKey: String): BookByBookNameAndAuthorDto
}