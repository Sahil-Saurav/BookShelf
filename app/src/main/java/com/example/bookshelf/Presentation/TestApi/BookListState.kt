package com.example.bookshelf.Presentation.TestApi

import com.example.bookshelf.Domain.Models.BookByBookNameAndAuthor

data class BookListState(
    val isLoading: Boolean = false,
    val books : BookByBookNameAndAuthor? = null,
    val error : String = ""
)
