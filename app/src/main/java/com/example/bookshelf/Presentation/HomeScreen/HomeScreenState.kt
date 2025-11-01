package com.example.bookshelf.Presentation.HomeScreen

import com.example.bookshelf.Data.Local.BookEntity

data class HomeScreenState(
    val isLoading : Boolean = false,
    val book : List<BookEntity> = emptyList<BookEntity>(),
    val currentlyReading : Int = 0,
    val notStarted : Int = 0,
    val finished : Int = 0,
    val total : Int = 0
)
