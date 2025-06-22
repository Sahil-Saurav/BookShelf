package com.example.bookshelf.Presentation.SearchAuthorScreen

import com.example.bookshelf.Domain.Models.BookByBookNameAndAuthor

data class SearchAuthorState(
    val isLoading : Boolean = false,
    val books : BookByBookNameAndAuthor? = null,
    val error : String = ""
)
