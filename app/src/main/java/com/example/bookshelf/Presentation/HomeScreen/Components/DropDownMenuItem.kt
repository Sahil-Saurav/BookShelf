package com.example.bookshelf.Presentation.HomeScreen.Components


data class DropDownMenuItem(
    val label : String,
    val type : String,
)
val menuItem = listOf<DropDownMenuItem>(
    DropDownMenuItem("About", AlertType.About_Alert),
    DropDownMenuItem("Sign-Out", AlertType.Sign_Out_Alert)
)
