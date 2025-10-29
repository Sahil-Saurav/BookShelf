package com.example.bookshelf.Utils.BottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.R

object BottomBarItems {
    val bottomBarItems = listOf<BottomBarItem>(
        BottomBarItem(
            idx = 0,
            label = "Home",
            route = Screen.HomeScreen.route,
            selectedIcon = { Icon(Icons.Default.Home, contentDescription = null)},
            unSelectedIcon = {Icon(Icons.Outlined.Home, contentDescription = null)}
        ),
        BottomBarItem(
            idx = 1,
            label = "Search",
            route = Screen.AuthorScreen.route,
            selectedIcon = { Icon(Icons.Default.Search, contentDescription = null)},
            unSelectedIcon = {Icon(Icons.Outlined.Search, contentDescription = null)}
        ),
        BottomBarItem(
            idx = 2,
            label = "BookShelf",
            route = Screen.BookShelfScreen.route,
            selectedIcon =  { Icon(painter = painterResource(R.drawable.baseline_shelves_24), contentDescription = null)},
            unSelectedIcon = {Icon(painter = painterResource(R.drawable.outline_shelves_24), contentDescription = null)}
        )
    )
}