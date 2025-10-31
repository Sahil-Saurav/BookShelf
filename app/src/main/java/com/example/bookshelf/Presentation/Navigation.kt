package com.example.bookshelf.Presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.AuthScreens.SignInScreen.SignInScreen
import com.example.bookshelf.Presentation.AuthScreens.SignUpScreen.SignUpScreen
import com.example.bookshelf.Presentation.BookDetailsScreen.BookDetailsScreen
import com.example.bookshelf.Presentation.BookDetailsScreen.BookViewModel
import com.example.bookshelf.Presentation.BookShelfScreen.BookShelfScreen
import com.example.bookshelf.Presentation.BookShelfScreen.BookShelfViewModel
import com.example.bookshelf.Presentation.HomeScreen.HomeScreen
import com.example.bookshelf.Presentation.Onboarding.OnBoardingViewModel
import com.example.bookshelf.Presentation.Onboarding.OnboardingPage
import com.example.bookshelf.Presentation.SearchScreen.SearchAuthorScreen
import com.example.bookshelf.Presentation.SearchScreen.SearchAuthorViewModel
import com.example.bookshelf.Presentation.SearchResultScreen.SearchResultScreenViewModel
import com.example.bookshelf.Presentation.SearchResultScreen.SearchResultScreen
import com.example.bookshelf.Presentation.SearchScreen.SearchByBookScreen
import com.example.bookshelf.R
import com.example.bookshelf.Utils.BottomNavBar.NavBar
import kotlinx.coroutines.launch

@Composable
fun Navigation(startDestination : String){
    val searchViewModel : SearchResultScreenViewModel = hiltViewModel()
    val authorViewModel : SearchAuthorViewModel = hiltViewModel()
    val bookViewModel : BookViewModel = hiltViewModel()
    val authViewModel : AuthViewModel = hiltViewModel()
    val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()
    val bookShelfViewModel : BookShelfViewModel = hiltViewModel()

    val coroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routesWithoutBottomBar = remember {
        setOf(
            Screen.OnBoarding.route,
            Screen.SignIn.route,
            Screen.SignUp.route
        )
    }

    val showBottomBar = currentRoute != null && currentRoute !in routesWithoutBottomBar


    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavBar(navController)
            }
        },
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->

        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.Primary_Background_Dark))
                .padding(innerPadding), // innerPadding will be correct even if bottomBar is null
            navController = navController,
            startDestination = startDestination) {
            composable(
                route = Screen.HomeScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }
            ) {
                HomeScreen(navController,authViewModel)
            }
            composable(
                route = Screen.SearchResultScreen.route+"/{bookName}",
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                val bookName = it.arguments?.getString("bookName")?:" "
                SearchResultScreen(bookName,searchViewModel,bookViewModel,navController)
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
                route = Screen.SearchScreen.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                SearchByBookScreen(
                    modifier = Modifier,
                    searchViewModel = searchViewModel,
                    authorViewModel = authorViewModel,
                    navController = navController
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

            composable(
                route = Screen.OnBoarding.route,
                enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }) {
                OnboardingPage(
                    onFinished = {
                        coroutineScope.launch {
                            onBoardingViewModel.onboardingFinished()
                        }
                        navController.navigate(Screen.SignIn.route) {
                            popUpTo(Screen.OnBoarding.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.BookShelfScreen.route,
                enterTransition = {slideInHorizontally(initialOffsetX = {-it}) },
                exitTransition = {slideOutHorizontally(targetOffsetX = {-it}) }){
                BookShelfScreen(navController,bookShelfViewModel)
            }
        }

    }

}