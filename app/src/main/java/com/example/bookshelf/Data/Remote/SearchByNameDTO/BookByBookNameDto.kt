package com.example.bookshelf.Data.Remote.SearchByNameDTO

import com.example.bookshelf.Domain.Models.BookByBookName

data class BookByBookNameDto(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)

fun BookByBookNameDto.toBookByBookName(): BookByBookName{
    return BookByBookName(
        items = items
    )
}