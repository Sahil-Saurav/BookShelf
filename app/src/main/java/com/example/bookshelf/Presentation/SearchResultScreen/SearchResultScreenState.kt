package com.example.bookshelf.Presentation.SearchResultScreen

import com.example.bookshelf.Domain.Models.BookByBookName

data class SearchResultScreenState(
    val isLoading:Boolean = false,
    val books: BookByBookName? = null,
    val error: String = ""
)
