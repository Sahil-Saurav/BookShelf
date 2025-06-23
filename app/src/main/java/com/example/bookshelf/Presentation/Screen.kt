package com.example.bookshelf.Presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object SearchScreen: Screen(route = "search_screen")
    object AuthorScreen: Screen(route = "author_screen")
    object BookDetailsScreen: Screen(route = "book_details_screen")
}