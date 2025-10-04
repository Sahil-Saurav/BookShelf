package com.example.bookshelf.Presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object SearchScreen: Screen(route = "search_screen")
    object AuthorScreen: Screen(route = "author_screen")
    object BookDetailsScreen: Screen(route = "book_details_screen")
    object SignIn: Screen(route = "sign_in_screen")
    object SignUp: Screen(route = "sign_up_screen")
}