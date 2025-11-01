package com.example.bookshelf.Presentation.HomeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.HomeScreen.Components.AlertType
import com.example.bookshelf.Presentation.HomeScreen.Components.CurrentlyReadingItem
import com.example.bookshelf.Presentation.HomeScreen.Components.HomeAlertBox
import com.example.bookshelf.Presentation.HomeScreen.Components.Legend
import com.example.bookshelf.Presentation.HomeScreen.Components.menuItem
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R

@Composable
fun HomeScreen(
    navController : NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val currentUser by authViewModel.currentUser.collectAsState()
    val state = homeViewModel.state.value
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()
    var showDropDown by remember { mutableStateOf(false) }
    var showAboutAlert by remember { mutableStateOf(false) }
    var showSignOutAlert by remember { mutableStateOf(false) }
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Finished",state.finished.toFloat(),colorResource(R.color.Primary_Font_Green)),
            PieChartData.Slice("Currently Reading",state.currentlyReading.toFloat(),colorResource(R.color.Rating_Star)),
            PieChartData.Slice("Not Started",state.notStarted.toFloat(), Color.Red),
        ),
        plotType = PlotType.Donut
    )
    val pieChartConfig = PieChartConfig(
        strokeWidth = 90f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        animationDuration = 1000,
        showSliceLabels = true,
        sliceLabelTextColor = Color.White,
        sliceLabelTextSize = 16.sp,
        labelVisible = true,
        labelColor = Color.White,
        labelFontSize = 32.sp,
        labelType = PieChartConfig.LabelType.VALUE,
        isSumVisible = true,
        sumUnit = "Books",
        backgroundColor = colorResource(R.color.Primary_Background_Dark),

    )
    val legend = listOf<Legend>(
        Legend("Finished", colorResource(R.color.Primary_Font_Green)),
        Legend("Not Started", Color.Red),
        Legend("Currently Reading", colorResource(R.color.Rating_Star)),
    )

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.Primary_Background_Dark))
    ) {
        LaunchedEffect(currentUser) {
            if(currentUser == null){
                navController.navigate(Screen.SignIn.route,navOptions{
                    popUpTo(Screen.HomeScreen.route){inclusive = true}
                    launchSingleTop = true
                })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dashboard",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = wdxllubrifont,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Column {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                showDropDown = true
                            }
                        )
                )
                Box {
                    DropdownMenu(
                        expanded = showDropDown,
                        onDismissRequest = {showDropDown = false},
                        containerColor = colorResource(R.color.Primary_Background_Dark),
                        border = BorderStroke(width = 2.dp, color = colorResource(R.color.Primary_Font_Green))
                    ) {
                        menuItem.forEach { item ->
                            DropdownMenuItem(
                                text = {Text(
                                    text = item.label,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontFamily = wdxllubrifont,
                                    fontWeight = FontWeight.SemiBold
                                    )
                                },
                                onClick = {
                                    when(item.type){
                                        AlertType.About_Alert -> {
                                            showAboutAlert = true
                                            showDropDown = false
                                        }
                                        AlertType.Sign_Out_Alert -> {
                                            showSignOutAlert = true
                                            showDropDown = false
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Column(
            modifier = Modifier
                .background(color = colorResource(R.color.Primary_Background_Dark))
                .verticalScroll(verticalScrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            DonutPieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.Primary_Background_Dark))
                    .padding(8.dp),
                pieChartData = pieChartData,
                pieChartConfig = pieChartConfig,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .horizontalScroll(horizontalScrollState)
            ){
                legend.forEach { legend ->
                    Box(
                        modifier = Modifier
                            .clip(shape = RectangleShape)
                            .background(legend.color)
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = legend.label,
                        color = legend.color,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = wdxllubrifont
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Currently Reading Book",
                fontFamily = wdxllubrifont,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                items(state.book) { book ->
                    CurrentlyReadingItem(book)
                }
            }
        }
    }
    if(showAboutAlert){
        HomeAlertBox(
            title = "About",
            description ="This Beautiful App is Developed by Sahil Saurav.\n Version = 1.0",
            buttonText = "Ok",
            onDismiss = {showAboutAlert = false},
            onClick = {showAboutAlert  = false}
        )
    }
    if (showSignOutAlert){
        HomeAlertBox(
            title = "Sign-Out",
            description = "Are you Sure you want to sign-out from this App.",
            buttonText = "Confirm",
            onDismiss = {showSignOutAlert = false},
            onClick = {
                authViewModel.signOut()
                Log.i("signout",currentUser.toString())
            }
        )
    }
}