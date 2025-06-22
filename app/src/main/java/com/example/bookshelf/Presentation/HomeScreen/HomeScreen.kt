package com.example.bookshelf.Presentation.HomeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
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
import androidx.navigation.NavHostController
import com.example.bookshelf.Presentation.Screen
import com.example.bookshelf.Presentation.SearchAuthorScreen.SearchAuthorViewModel
import com.example.bookshelf.Presentation.SearchScreen.SearchScreenViewModel
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R
import com.example.bookshelf.Utils.TextFieldStyle

@Composable
fun HomeScreen(
    modifier: Modifier,
    searchViewModel: SearchScreenViewModel = hiltViewModel(),
    authorViewModel: SearchAuthorViewModel = hiltViewModel(),
    navController: NavHostController
)
{
    //val searchState = searchViewModel.state

    var bookName by remember { mutableStateOf<String>("") }
    var keyBoardController = LocalSoftwareKeyboardController.current
    Box(modifier = modifier
        .fillMaxSize()
        .background(colorResource(R.color.Primary_Background_Dark))
        .padding(8.dp)
        .systemBarsPadding()
        )
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                text = "Welcome,to BookShelves",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = wdxllubrifont
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)) {
            Spacer(modifier.height(32.dp))
            TextField(
                value = authorViewModel.book.value,
                onValueChange = {
                    bookName = it
                    authorViewModel.setBookName(bookName)
                                },
                singleLine = true,
                label = {Text("Enter Book Name", fontFamily = wdxllubrifont, color = colorResource(R.color.Primary_Background_Dark))},
                textStyle = TextFieldStyle(
                    color = colorResource(R.color.Primary_Font_Green),
                    fontWeight = FontWeight.Bold,
                    background = colorResource(R.color.Primary_Background_Dark)
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Log.i("Done IME","Keyboard is Hide")
                        keyBoardController?.hide()
                    }
                )
            )
            Spacer(modifier.height(8.dp))
            Button(
                onClick = {
                    searchViewModel.getBookByName(bookName)
                    authorViewModel.setBookName(bookName)
                    navController.navigate(route = Screen.SearchScreen.route+"/${bookName}")
                    Log.i("Search Button","$bookName is Searched")
                    keyBoardController?.hide()
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
            Spacer(modifier.height(4.dp))
            HorizontalDivider(modifier.padding(start = 8.dp, end = 8.dp))
            Spacer(modifier.height(8.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.AuthorScreen.route)
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
                    text = "Search Book by Author",
                    fontFamily = wdxllubrifont
                )
            }
//            if(state.value.isLoading){
//                CircularProgressIndicator(
//                    color = colorResource(R.color.Primary_Font_Green),
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                    )
//            }
//            if(!state.value.error.isEmpty()){
//                Text(
//                    text = state.value.error,
//                    fontFamily = wdxllubrifont,
//                    color = Color.Red
//                )
//            }
//            if(state.value.books?.items?.isNotEmpty() == true){
//                LazyColumn() {
//                    items(state.value.books?.items?:emptyList()) { book->
//                        BookListCard(book)
//                        Spacer(modifier = Modifier.height(8.dp))
//                }
//                }
//            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    //HomeScreen(modifier = Modifier)
}