package com.example.bookshelf.Domain.Repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val hasCompletedOnBoarding : Flow<Boolean>
    suspend fun onOnboardingFinished()
}