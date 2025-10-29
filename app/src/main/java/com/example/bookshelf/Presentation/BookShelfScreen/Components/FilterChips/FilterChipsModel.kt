package com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips

import com.example.bookshelf.Common.Filter

data class FilterChipsModel(
    val id : Int,
    val name : String,
    val type : String
)

val filterChips = listOf<FilterChipsModel>(
    FilterChipsModel(0,"All", Filter.ALL),
    FilterChipsModel(1,"Currently Reading", Filter.CURRENTLY_READING),
    FilterChipsModel(2,"Not Started", Filter.NOT_STARTED),
    FilterChipsModel(3,"Finished", Filter.FINISHED),
)
