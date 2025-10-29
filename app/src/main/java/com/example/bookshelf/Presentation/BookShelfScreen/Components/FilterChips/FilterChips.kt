package com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R

@Composable
fun FilterChips(text:String,selected: Boolean,onClick:()-> Unit){

    FilterChip(
        selected = selected,
        onClick = {onClick()},
        label = {Text(text)},
        colors = SelectableChipColors(
            containerColor = colorResource(R.color.Primary_Background_Dark),
            labelColor = Color.White,
            disabledContainerColor = Color.White,
            disabledLabelColor = Color.White,
            disabledLeadingIconColor = Color.White,
            disabledTrailingIconColor = Color.White,
            selectedContainerColor = colorResource(R.color.Primary_Font_Green),
            disabledSelectedContainerColor = Color.White,
            selectedLabelColor = Color.Black,
            selectedLeadingIconColor = Color.White,
            selectedTrailingIconColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White
        ),
        border = BorderStroke(width = 1.dp, color = colorResource(R.color.Primary_Font_Green))
    )
}