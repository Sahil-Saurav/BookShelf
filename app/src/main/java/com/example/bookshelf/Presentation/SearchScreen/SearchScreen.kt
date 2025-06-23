package com.example.bookshelf.Presentation.SearchScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bookshelf.Presentation.BookDetailsScreen.BookViewModel
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.Presentation.SearchScreen.Components.BookListCard
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R

@Composable
fun SearchScreen(
    bookName: String,
    viewModel: SearchScreenViewModel = hiltViewModel(),
    bookViewModel: BookViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Primary_Background_Dark))
            .padding(8.dp)
            .systemBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(state.value.isLoading){
                CircularProgressIndicator(
                    color = colorResource(R.color.Primary_Font_Green)
                )
                Text(
                    text = "Searching for $bookName",
                    fontFamily = wdxllubrifont,
                    color = Color.White
                )
            }
            if(!state.value.isLoading && state.value.books?.items.isNullOrEmpty() && state.value.error.isEmpty()){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Search Results for: ${bookName}",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = wdxllubrifont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cannot find: $bookName",
                        fontFamily = wdxllubrifont,
                        color = Color.Red
                    )
                }
            }
            if(!state.value.error.isEmpty()){
                Text(
                    text = state.value.error,
                    fontFamily = wdxllubrifont,
                    color = Color.Red
                )
            }
            if(state.value.books?.items?.isNotEmpty() == true){
                Row(
                    modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                            )
                    }
                    Text(
                        text = "Search Results for: ${bookName}",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = wdxllubrifont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value.books?.items?:emptyList()) { book->
                        BookListCard(book, onClick = {
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
fun HomeScreenPreview(){
    //HomeScreen(modifier = Modifier)
}