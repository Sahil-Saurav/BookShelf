package com.example.bookshelf.Presentation.SearchAuthorScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.Item
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R

@Composable
fun AuthorBookListCard(book: Item,onClick:()-> Unit){
    Column(
        modifier = Modifier
            .clip(shape = AbsoluteCutCornerShape(bottomRight = 48.dp))
            .background(colorResource(R.color.Primary_Background_Dark))
            .border(width = 2.dp, color = colorResource(R.color.Primary_Font_Green), shape = AbsoluteCutCornerShape(bottomRight = 48.dp))
            //.shadow(elevation = 8.dp, shape = AbsoluteCutCornerShape(bottomRight = 48.dp), ambientColor = colorResource(R.color.Primary_Font_Green), spotColor = colorResource(R.color.Primary_Font_Green))
            .padding(2.dp)
            .fillMaxWidth()
            .clickable(onClick = {onClick()})
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ){
            Box {
                val imageLinks = book.volumeInfo.imageLinks
                if (imageLinks != null) {
                    val thumbnailUrl = imageLinks.smallThumbnail.replace("http","https")
                    if (thumbnailUrl != null) {
                        // Load the image
                        AsyncImage(
                            model = thumbnailUrl,
                            contentDescription = "Book thumbnail",
                            modifier = Modifier.size(120.dp)
                        )
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
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(2.dp)
                        .background(color = colorResource(R.color.Primary_Background_Dark))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rating_star),
                        contentDescription = "Rating",
                        tint = colorResource(R.color.Rating_Star),
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${book.volumeInfo.averageRating?:"N/A"}",
                        color = colorResource(R.color.Rating_Star),
                        fontSize = 16.sp,
                        fontFamily = wdxllubrifont
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = book.volumeInfo.title,
                    fontSize = 24.sp,
                    fontFamily = wdxllubrifont,
                    color = colorResource(R.color.Primary_Font_Green),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "   -${book.volumeInfo.authors?.get(0)?:"Anonymous"}",
                    fontSize = 16.sp,
                    fontFamily = wdxllubrifont,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Publisher: ${book.volumeInfo.publisher}",
                    fontSize = 16.sp,
                    fontFamily = wdxllubrifont,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Published Date: ${book.volumeInfo.publishedDate}",
                    fontSize = 16.sp,
                    fontFamily = wdxllubrifont,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
@Preview
fun BookListPreview(){
    //BookListCard()
}