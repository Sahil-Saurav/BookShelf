package com.example.bookshelf.Presentation.SearchScreen

import com.example.bookshelf.Domain.Models.BookByBookName

data class SearchState(
    val isLoading:Boolean = false,
    val books: BookByBookName? = null,
    val error: String = ""
)
