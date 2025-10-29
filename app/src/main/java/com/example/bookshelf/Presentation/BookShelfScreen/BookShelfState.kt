package com.example.bookshelf.Presentation.BookShelfScreen

import com.example.bookshelf.Common.Filter
import com.example.bookshelf.Data.Local.BookEntity

data class BookShelfState(
    val isLoading : Boolean = false,
    val book : List<BookEntity> = emptyList<BookEntity>(),
    val error : String = "",
    val filter : String = Filter.ALL
)
