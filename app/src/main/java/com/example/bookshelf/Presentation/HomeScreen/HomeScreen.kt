package com.example.bookshelf.Presentation.HomeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R

@Composable
fun HomeScreen(
    navController : NavController,
    authViewModel: AuthViewModel = hiltViewModel()
){
    val currentUser by authViewModel.currentUser.collectAsState()
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.Primary_Background_Dark))
            .fillMaxSize()
    ) {
        LaunchedEffect(currentUser) {
            if(currentUser == null){
                navController.navigate(Screen.SignIn.route,navOptions{
                    popUpTo(Screen.HomeScreen.route){inclusive = true}
                    launchSingleTop = true
                })
            }
        }
        Text(
            text = "Dashboard",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = wdxllubrifont,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.signOut()
                Log.i("signout",currentUser.toString())
            },
            colors = ButtonColors(
                containerColor = colorResource(R.color.Primary_Font_Green),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            ),
            border = BorderStroke(
                width = 2.dp,
                color = Color.White
            )
        ){
            Text(
                text = "Sign Out",
                fontFamily = wdxllubrifont
            )
        }
    }
}