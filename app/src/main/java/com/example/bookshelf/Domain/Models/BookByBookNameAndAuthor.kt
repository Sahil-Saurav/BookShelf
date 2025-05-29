package com.example.bookshelf.Domain.Models

import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.Item

data class BookByBookNameAndAuthor(
    val items: List<Item>?,
)