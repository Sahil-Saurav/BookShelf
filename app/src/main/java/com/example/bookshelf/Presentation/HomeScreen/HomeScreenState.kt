package com.example.bookshelf.Presentation.HomeScreen

import com.example.bookshelf.Data.Local.BookEntity

data class HomeScreenState(
    val isLoading: Boolean = false,
    val book : List<BookEntity> = emptyList<BookEntity>(),
    val error : String = ""
)
