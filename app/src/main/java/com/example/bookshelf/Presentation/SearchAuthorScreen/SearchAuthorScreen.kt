package com.example.bookshelf.Presentation.SearchAuthorScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookshelf.Presentation.BookDetailsScreen.BookViewModel
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.Presentation.SearchAuthorScreen.Components.AuthorBookListCard
import com.example.bookshelf.Presentation.SearchScreen.Components.BookListCard
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R
import com.example.bookshelf.Utils.TextFieldStyle

@Composable
fun SearchAuthorScreen(
    authorViewModel: SearchAuthorViewModel = hiltViewModel(),
    bookViewModel: BookViewModel = hiltViewModel(),
    navController: NavController
){
    var authorName by remember { mutableStateOf("") }
    var keyboardController = LocalSoftwareKeyboardController.current
    val state = authorViewModel.state
    var searchClick by remember { mutableStateOf<Boolean>(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Primary_Background_Dark))
            .padding(start = 8.dp, end = 8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(

                text = "Search Books by Author",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = wdxllubrifont
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            TextField(
                value = authorName,
                onValueChange = {authorName = it},
                singleLine = true,
                textStyle = TextFieldStyle(
                    color = colorResource(R.color.Primary_Font_Green),
                    fontWeight = FontWeight.Bold,
                    background = colorResource(R.color.Primary_Background_Dark)
                ),
                label = {Text(text = "Author Name:", fontFamily = wdxllubrifont)},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    authorViewModel.getBookByAuthorName(authorName)
                    Log.i("Search Button","$authorName is Searched")
                    searchClick = true
                    keyboardController?.hide()
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
                    text = "Search",
                    fontFamily = wdxllubrifont
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            if(state.value.isLoading){
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    CircularProgressIndicator(
                        color = colorResource(R.color.Primary_Font_Green)
                    )
                    Text(
                        text = "Searching for $authorName",
                        fontFamily = wdxllubrifont,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

            }
            if(!state.value.error.isEmpty()){
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = state.value.error,
                        fontFamily = wdxllubrifont,
                        color = Color.Red
                    )
                }

            }
            if(!state.value.isLoading && state.value.books?.items.isNullOrEmpty() && state.value.error.isEmpty() && searchClick){
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cannot find: $authorName",
                        fontFamily = wdxllubrifont,
                        color = Color.Red
                    )
                }
            }
            if(state.value.books?.items?.isNotEmpty() == true){
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value.books?.items?:emptyList()) { book->
                        AuthorBookListCard(book, onClick = {
                            bookViewModel.getBook(book.id)
                            navController.navigate(Screen.BookDetailsScreen.route)
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchPreview(){
    //SearchAuthorScreen()
}