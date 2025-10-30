package com.example.bookshelf.Presentation.BookShelfScreen.Components.BookShelfItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
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
fun BookShelfItem(
    bookEntity: BookEntity,
    onDeleteClick:()-> Unit,
    onUpdateClick:()->Unit){
    Column(
        modifier = Modifier
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

        /*Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.book_sign_in),
            contentDescription = null,
            alignment = Alignment.Center
        )*/
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
                text = if(bookEntity.finishedReading == true) "Finished" else if(bookEntity.currentlyReading == true) "Currently Reading" else "Not Started",
                color = if(bookEntity.finishedReading == true) colorResource(R.color.Primary_Font_Green) else if(bookEntity.currentlyReading == true) colorResource(R.color.Rating_Star) else Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Color.Red,
                contentDescription = "Delete Button",
                modifier = Modifier
                    .clickable(onClick = {onDeleteClick()})
            )
            Icon(
                imageVector = Icons.Default.Edit,
                tint = Color.Gray,
                contentDescription = "Edit Button",
                modifier = Modifier
                    .clickable(onClick = {onUpdateClick()})
            )
        }
    }
}

//@Preview
//@Composable
//fun ItemPreview(){
//    val book = BookEntity(
//        id = "D0qBEQAAQBAJ",
//        currentlyReading = false,
//        finishedReading = false,
//        image = "http://books.google.com/books/publisher/content?id=D0qBEQAAQBAJ&printsec=frontcover&img=1&zoom=4&edge=curl&imgtk=AFLRE73ylEeW6hJTHn-l8nlVSHV2VbPnFQ5ifhr6IxhjqnvAUaJm69KVr_dR82QT7docz0lQ0JQY9bDO13ElDaajfZW0vARWt-FTwD-QK4AdqFIxDYDIw3AfBUXqDEeoYnHb9Z0xIkqt&source=gbs_api",
//        title = "The Almanack of Naval Ravikant"
//    )
//    BookShelfItem(book)
//}