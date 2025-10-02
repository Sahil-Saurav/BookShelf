package com.example.bookshelf.Presentation.BookDetailsScreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R
import com.example.bookshelf.Utils.cleanHtmlTags

@Composable
fun BookDetailsScreen(
    bookViewModel: BookViewModel = hiltViewModel(),
    navController: NavController
){
    val state = bookViewModel.state
    val book = bookViewModel.state.value.book
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.Primary_Background_Dark))
            .padding(8.dp)
            .systemBarsPadding()
    ) {
        if(state.value.isLoading == true){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = colorResource(R.color.Primary_Font_Green))
            }
        }else{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    IconButton(
                        onClick = {navController.navigateUp()},
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate Up",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Book Details",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = wdxllubrifont
                    )
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val imageLinks = book?.volumeInfo?.imageLinks
                            if (imageLinks != null) {
                                val thumbnailUrl = imageLinks.thumbnail.replace("http","https")
                                if (thumbnailUrl != null) {
                                    // Load the image
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .fillMaxWidth()
                                    ){
                                        AsyncImage(
                                            model = thumbnailUrl,
                                            contentDescription = "Book thumbnail",
                                            contentScale = ContentScale.FillBounds, // Optional: crop to fill the box
                                            modifier = Modifier
                                                .matchParentSize()
                                                .blur(16.dp)
                                        )
                                        AsyncImage(
                                            model = thumbnailUrl,
                                            contentDescription = "Book thumbnail",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier.matchParentSize()
                                    )

                                    }
                                } else {
                                    // Handle case where thumbnail specifically is null
                                    Image(
                                        painter = painterResource(R.drawable.brokenimage),
                                        contentDescription = "Thumbnail missing",
                                        modifier = Modifier
                                            .size(120.dp)
                                            .background(color = Color.Green)
                                    )
                                }
                            } else {
                                // Handle case where imageLinks is null
                                Image(
                                    painter = painterResource(R.drawable.brokenimage),
                                    contentDescription = "Thumbnail missing",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .background(color = Color.Green)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = book?.volumeInfo?.title?:"No title found",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.Primary_Font_Green),
                                fontFamily = wdxllubrifont
                            )
                        }

                    }
                    item {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()) {
                                Text(
                                    text = "Author : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.authors?.get(0)?:" ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()) {
                                Text(
                                    text = "Published Date : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.publishedDate?:" ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()) {
                                Text(
                                    text = "Publisher : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.publisher?:" ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Rating : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.averageRating.toString(),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Genre : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.categories?.get(0)?:" ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                            Text(
                                text = "Description : ",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.Primary_Font_Green),
                                fontFamily = wdxllubrifont
                            )
                            Text(
                                text = cleanHtmlTags(book?.volumeInfo?.description?:" "),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontFamily = wdxllubrifont
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Page Count : ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(R.color.Primary_Font_Green),
                                    fontFamily = wdxllubrifont
                                )
                                Text(
                                    text = book?.volumeInfo?.printedPageCount.toString(),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun BookDetailsPreview(){
    //BookDetailsScreen()
}