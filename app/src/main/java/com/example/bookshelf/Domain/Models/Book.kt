package com.example.bookshelf.Domain.Models

import com.example.bookshelf.Data.Remote.SearchBook.SaleInfo
import com.example.bookshelf.Data.Remote.SearchBook.VolumeInfo

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
)
