package com.example.bookshelf.Utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont


fun TextFieldStyle(
    color: Color,
    fontWeight: FontWeight,
    background: Color
): TextStyle{
    return TextStyle(
        color = color,
        fontSize = 16.sp,
        fontFamily = wdxllubrifont,
        fontWeight = fontWeight,
        fontStyle = FontStyle.Italic,
        letterSpacing = 0.1.em,
        background = background,
        textDecoration = TextDecoration.Underline
    )
}