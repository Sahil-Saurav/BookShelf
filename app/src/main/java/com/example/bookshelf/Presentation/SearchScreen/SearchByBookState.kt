package com.example.bookshelf.Presentation.SearchScreen

import com.example.bookshelf.Data.Local.BookEntity

data class SearchByBookState(
    val isLoading: Boolean = false,
    val book : List<BookEntity> = emptyList<BookEntity>(),
    val error : String = ""
)
