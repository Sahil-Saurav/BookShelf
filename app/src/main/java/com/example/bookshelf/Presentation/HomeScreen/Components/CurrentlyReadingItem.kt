package com.example.bookshelf.Presentation.HomeScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.R

@Composable
fun CurrentlyReadingItem(
    bookEntity: BookEntity){
    Column(
        modifier = Modifier
            .width(200.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(R.color.Primary_Background_Dark))
            .border(width = 4.dp, color = colorResource(R.color.Primary_Font_Green), shape = RoundedCornerShape(16.dp))
            .padding(8.dp)
    ){
        if(bookEntity.image.isNotEmpty()){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                model = bookEntity.image,
                contentDescription = "book image",
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
            )
        }else{
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painterResource(R.drawable.brokenimage),
                contentDescription = null
            )
        }
        Row {
            Text(
                text = "Title: ",
                color = colorResource(R.color.Primary_Font_Green),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
            Text(
                text = bookEntity.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
        Row {
            Text(
                text = "Status: ",
                color = colorResource(R.color.Primary_Font_Green),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
            Text(
                text = if(bookEntity.finishedReading == true) "Finished" else if(bookEntity.currentlyReading == true) "Reading" else "Not Started",
                color = if(bookEntity.finishedReading == true) colorResource(R.color.Primary_Font_Green) else if(bookEntity.currentlyReading == true) colorResource(R.color.Rating_Star) else Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
    }
}