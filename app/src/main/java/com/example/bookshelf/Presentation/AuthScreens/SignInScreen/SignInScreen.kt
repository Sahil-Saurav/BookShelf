package com.example.bookshelf.Presentation.AuthScreens.SignInScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R
import com.example.bookshelf.Utils.CustomAppM3TextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.bookshelf.Common.AuthResult
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.Screen

@Composable
fun SignInScreen(navController: NavController,
                 authViewModel: AuthViewModel = hiltViewModel()
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(authState) {
        if(authState is AuthResult.Success){
            navController.navigate(Screen.HomeScreen.route,navOptions {
                popUpTo(Screen.SignIn.route) { inclusive = true }
                launchSingleTop = true
            })
        }
    }

    LaunchedEffect(Unit) {
        authViewModel.resetState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.Primary_Background_Dark))
            .padding(8.dp)
            .systemBarsPadding(),
        //contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Log-In to your Account",
                fontSize = 32.sp,
                color = Color.White,
                fontFamily = wdxllubrifont
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Sign In",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontFamily = wdxllubrifont
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(R.drawable.book_sign_in),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }
            Text(
                text = "Email:",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = wdxllubrifont
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomAppM3TextField(
                value = email,
                onValueChange = {email = it},
                label = "Email",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Password:",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = wdxllubrifont
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomAppM3TextField(
                value = password,
                onValueChange = {password = it},
                label = "Password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    authViewModel.signIn(email,password)
                }),
                trailingIcon = { IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        painter = if(passwordVisible) painterResource(R.drawable.eye_blind_icon) else painterResource(R.drawable.view_icon),
                        tint = colorResource(R.color.Primary_Font_Green),
                        contentDescription = null
                    )
                } },
                visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if(authState is AuthResult.Loading){
                    CircularProgressIndicator(
                        color = colorResource(R.color.Primary_Font_Green)
                    )
                }else{
                    Button(
                        onClick = {
                            authViewModel.signIn(email,password)
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
                            text = "Sign In",
                            fontFamily = wdxllubrifont
                        )
                    }
                }
                if(authState is AuthResult.Error){
                    Text(
                        text = (authState as AuthResult.Error).message,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Red,
                        fontFamily = wdxllubrifont
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Don't have an account create a new one\nSign-Up",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = wdxllubrifont,
                    modifier = Modifier
                        .clickable(
                            onClick = {navController.navigate(Screen.SignUp.route)}
                        )
                )
            }
        }
    }
}

@Composable
@Preview
fun signInPreview(){
    //SignInScreen()
}