package com.example.bookshelf.Presentation.Onboarding.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R


@Composable
fun OnboardingPageItem(
    page: OnboardingPages,
    pagerState: PagerState,
    onFinishClick:()->Unit,
    onNextClick:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.Primary_Background_Dark)),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(page.imageId),
                contentDescription = "Onboarding Image"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                .background(colorResource(R.color.Primary_Font_Green))
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = page.title,
                fontSize = 32.sp,
                color = Color.White,
                fontFamily = wdxllubrifont,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = page.description?:"",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = wdxllubrifont,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.Primary_Background_Dark),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    onClick = {
                        if(pagerState.currentPage == pagerState.pageCount-1){
                            onFinishClick()
                        }else{
                            onNextClick()
                        }
                    }
                ){
                    Text(
                        text = if(pagerState.currentPage == pagerState.pageCount-1)"Finish" else " Next "
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ItemPreview(){
    //val page = OnboardingPages(imageId = R.drawable.book_sign_in, title = "Search Your Books", description = "Search your favourite books,or the book you're going to read.\nAdd to your BookShelf and track the progress how many books you have read.")
    //OnboardingPageItem(page)
}