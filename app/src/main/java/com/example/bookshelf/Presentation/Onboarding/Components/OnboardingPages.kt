package com.example.bookshelf.Presentation.Onboarding.Components

import androidx.annotation.DrawableRes

data class OnboardingPages(
    @DrawableRes val imageId: Int,
    val title : String,
    val description : String? = null
)
