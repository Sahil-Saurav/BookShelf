package com.example.bookshelf.Data.Repository

import com.example.bookshelf.Data.GoogleBooksApi
import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.BookByBookNameAndAuthorDto
import com.example.bookshelf.Domain.Repository.BookShelfRepository

import com.example.bookshelf.Data.Remote.SearchByNameDTO.BookByBookNameDto
import javax.inject.Inject

class BookShelfRepositoryImpl @Inject constructor(
    private val api: GoogleBooksApi
) : BookShelfRepository {
    override suspend fun getBookByName(
        bookName: String,
        apiKey: String
    ): BookByBookNameDto {
        val query = "intitle:$bookName"
        return api.getBookByName(query,apiKey)
    }

    override suspend fun getBookByNameAndAuthor(
        bookName: String,
        authorName: String,
        apiKey: String
    ): BookByBookNameAndAuthorDto {
        val query = "$bookName+inauthor:$authorName"
        return api.getBookByNameAndAuthor(query,apiKey)
    }
}