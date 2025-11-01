package com.example.bookshelf.Presentation.HomeScreen.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAlertBox(
    title : String,
    description : String,
    buttonText : String,
    onDismiss:()->Unit,
    onClick:()->Unit = {}
){
    BasicAlertDialog(
        onDismissRequest = {onDismiss()},
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(colorResource(R.color.Primary_Background_Dark))
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = wdxllubrifont
            )
            Text(
                text = description,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontFamily = wdxllubrifont
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        onClick()
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
                        text = buttonText,
                        fontFamily = wdxllubrifont
                    )
                }
            }
        }
    }
}