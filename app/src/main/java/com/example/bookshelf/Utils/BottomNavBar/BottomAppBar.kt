package com.example.bookshelf.Utils.BottomNavBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.bookshelf.R

@Composable
fun NavBar(navController: NavController){
    var selectedDestination by rememberSaveable { mutableIntStateOf(BottomBarItems.bottomBarItems[0].idx) }
    NavigationBar(
        contentColor = colorResource(R.color.Primary_Font_Green),
        containerColor = colorResource(R.color.Primary_Font_Green),
    ) {
        BottomBarItems.bottomBarItems.forEachIndexed { index,destination ->
            NavigationBarItem(
                selected = index == selectedDestination,
                onClick = {
                    navController.navigate(route = destination.route)
                    selectedDestination = destination.idx
                          },
                icon = { if (index == selectedDestination) destination.selectedIcon() else destination.unSelectedIcon()},
                label = { Text(text = destination.label) },
                enabled = index != selectedDestination,
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = colorResource(R.color.Primary_Background_Dark),
                    unselectedIconColor = colorResource(R.color.Primary_Background_Dark),
                    unselectedTextColor = colorResource(R.color.Primary_Background_Dark),
                    disabledIconColor = Color.White,
                    disabledTextColor = Color.White
                )
            )
        }
    }
}