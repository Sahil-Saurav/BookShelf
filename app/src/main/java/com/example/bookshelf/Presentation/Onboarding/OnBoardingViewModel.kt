package com.example.bookshelf.Presentation.Onboarding


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Domain.UseCases.OnBoardingFinishedUseCase.OnBoardingFinishedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingFinishedUseCase: OnBoardingFinishedUseCase) : ViewModel() {
    val hasCompletedOnBoarding : Flow<Boolean> = onBoardingFinishedUseCase.hasFinishedOnBoarding

    fun onboardingFinished(){
        viewModelScope.launch {
            onBoardingFinishedUseCase()
        }
    }
}