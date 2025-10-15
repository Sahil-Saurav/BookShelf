package com.example.bookshelf.Domain.UseCases.OnBoardingFinishedUseCase

import com.example.bookshelf.Domain.Repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingFinishedUseCase @Inject constructor(private val repository: SettingsRepository) {

    val hasFinishedOnBoarding:Flow<Boolean> = repository.hasCompletedOnBoarding

    suspend operator fun invoke() {
        repository.onOnboardingFinished()
    }
}