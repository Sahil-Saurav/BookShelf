package com.example.bookshelf.Presentation.Onboarding

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.Presentation.Onboarding.Components.OnboardingPageItem
import com.example.bookshelf.Presentation.Onboarding.Components.onBoardingPages
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage(onFinished:()-> Unit){
    val pagerState = rememberPagerState(pageCount = {onBoardingPages.size})
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {page->
            OnboardingPageItem(
                page = onBoardingPages[page],
                pagerState = pagerState,
                onNextClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage+1)
                    }
                },
                onFinishClick = {onFinished()}
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                repeat(onBoardingPages.size) { iteration->
                    val color = if(iteration == pagerState.currentPage) Color.White else colorResource(R.color.background_dark)
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(12.dp)
                    )
                }
            }
        }
    }

}
