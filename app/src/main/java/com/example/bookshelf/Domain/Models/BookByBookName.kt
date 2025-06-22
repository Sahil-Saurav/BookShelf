package com.example.bookshelf.Domain.Models

import com.example.bookshelf.Data.Remote.SearchByNameDTO.Item

data class BookByBookName(
    val items: List<Item>?
)
