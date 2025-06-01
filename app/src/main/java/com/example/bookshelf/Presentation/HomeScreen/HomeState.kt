package com.example.bookshelf.Presentation.HomeScreen

import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Domain.Models.BookByBookName

data class HomeState(
    val isLoading:Boolean = false,
    val books: BookByBookName? = null,
    val error: String = ""
)
