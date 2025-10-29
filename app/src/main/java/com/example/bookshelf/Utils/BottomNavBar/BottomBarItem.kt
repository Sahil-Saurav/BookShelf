package com.example.bookshelf.Utils.BottomNavBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val idx : Int,
    val label : String,
    val route : String,
    val selectedIcon: @Composable () -> Unit,
    val unSelectedIcon: @Composable () -> Unit,
)
