package com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO

import com.example.bookshelf.Domain.Models.BookByBookNameAndAuthor

data class BookByBookNameAndAuthorDto(
    val items: List<Item>?,
    val kind: String,
    val totalItems: Int
)

fun BookByBookNameAndAuthorDto.toBookByNameAndAuthor(): BookByBookNameAndAuthor{
    return BookByBookNameAndAuthor(
        items = items
    )
}