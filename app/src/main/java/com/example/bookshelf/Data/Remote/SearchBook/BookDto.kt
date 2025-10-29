package com.example.bookshelf.Data.Remote.SearchBook

import com.example.bookshelf.Domain.Models.Book

data class BookDto(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val layerInfo: LayerInfo,
    val saleInfo: SaleInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)

fun BookDto.toBook(): Book{
    return Book(
        id = id,
        volumeInfo = volumeInfo,
        saleInfo = saleInfo
    )
}