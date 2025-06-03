package com.example.bookshelf.Presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.Presentation.HomeScreen.HomeScreen
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
        val homeViewModel : SearchScreenViewModel = hiltViewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(
                route = Screen.HomeScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }
            ) {
                HomeScreen(modifier = Modifier,homeViewModel,navController)
            }
            composable(
                route = Screen.SearchScreen.route+"/{bookName}",
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                val bookName = it.arguments?.getString("bookName")?:" "
                SearchScreen(bookName,homeViewModel,navController)
            }
        }
    }
}