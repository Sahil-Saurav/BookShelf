package com.example.bookshelf.Presentation.BookDetailsScreen

import com.example.bookshelf.Domain.Models.Book

data class BookScreenState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val error: String = ""
)
