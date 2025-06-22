package com.example.bookshelf.Data


import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.BookByBookNameAndAuthorDto
import com.example.bookshelf.Data.Remote.SearchByNameDTO.BookByBookNameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun getBookByName(
        @Query("q")bookName: String,
        @Query("key")apiKey: String
    ): BookByBookNameDto

    @GET("volumes")
    suspend fun getBookbyAuthor(
        @Query("q")query:String,
        @Query("maxResults")max: Int,
        @Query("key")apiKey: String
    ): BookByBookNameAndAuthorDto
}