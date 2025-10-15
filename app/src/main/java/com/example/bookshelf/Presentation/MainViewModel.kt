package com.example.bookshelf.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Domain.Repository.AuthRepository
import com.example.bookshelf.Domain.Repository.SettingsRepository
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.OnBoardingFinishedUseCase.OnBoardingFinishedUseCase
import com.example.bookshelf.Presentation.AuthScreens.AuthViewModel
import com.example.bookshelf.Presentation.Onboarding.OnBoardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingFinishedUseCase: OnBoardingFinishedUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading  = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val hasFinishedOnBoarding = onBoardingFinishedUseCase.hasFinishedOnBoarding.first()
            val currentUser = authUseCase.GetCurrentUserIdUseCase()?.first()

            _startDestination.value = if (!hasFinishedOnBoarding) {
                Screen.OnBoarding.route
            } else if (currentUser == null) {
                Screen.SignIn.route
            } else {
                Screen.HomeScreen.route
            }
            _isLoading.value = false
        }
    }
}