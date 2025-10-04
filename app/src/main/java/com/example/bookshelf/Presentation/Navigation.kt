package com.example.bookshelf.Presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.AuthScreens.SignInScreen.SignInScreen
import com.example.bookshelf.Presentation.AuthScreens.SignUpScreen.SignUpScreen
import com.example.bookshelf.Presentation.BookDetailsScreen.BookDetailsScreen
import com.example.bookshelf.Presentation.BookDetailsScreen.BookViewModel
import com.example.bookshelf.Presentation.HomeScreen.HomeScreen
import com.example.bookshelf.Presentation.SearchAuthorScreen.SearchAuthorScreen
import com.example.bookshelf.Presentation.SearchAuthorScreen.SearchAuthorViewModel
import com.example.bookshelf.Presentation.SearchScreen.SearchScreenViewModel
import com.example.bookshelf.Presentation.SearchScreen.SearchScreen
import com.example.bookshelf.R

@Composable
fun Navigation(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Primary_Background_Dark))
    ) {
        val searchViewModel : SearchScreenViewModel = hiltViewModel()
        val authorViewModel : SearchAuthorViewModel = hiltViewModel()
        val bookViewModel : BookViewModel = hiltViewModel()
        val authViewModel : AuthViewModel = hiltViewModel()
        val navController = rememberNavController()
        val currentUser by authViewModel.currentUser.collectAsState()

        val startDestination = if(currentUser != null) Screen.HomeScreen.route else Screen.SignIn.route

        NavHost(navController = navController, startDestination = startDestination) {
            composable(
                route = Screen.HomeScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }
            ) {
                HomeScreen(modifier = Modifier,searchViewModel,authorViewModel,authViewModel,navController)
            }
            composable(
                route = Screen.SearchScreen.route+"/{bookName}",
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                val bookName = it.arguments?.getString("bookName")?:" "
                SearchScreen(bookName,searchViewModel,bookViewModel,navController)
            }
            composable(
                route = Screen.AuthorScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                SearchAuthorScreen(
                    navController = navController,
                    bookViewModel = bookViewModel
                )
            }
            composable(
                route = Screen.BookDetailsScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                BookDetailsScreen(bookViewModel,navController)
            }
            composable(
                route = Screen.SignIn.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                SignInScreen(navController,authViewModel)
            }
            composable(
                route = Screen.SignUp.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                SignUpScreen(navController,authViewModel)
            }
        }
    }
}