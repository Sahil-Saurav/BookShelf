package com.example.bookshelf.Data.Remote.SearchByNameDTO

data class Offer(
    val finskyOfferType: Int,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)