package com.example.bookshelf.Data.Repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.bookshelf.Domain.Repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl (private val context: Context) : SettingsRepository{

    companion object{
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "onboarding")
        val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey(name = "onboarding_completed")
    }

    override val hasCompletedOnBoarding: Flow<Boolean> = context.datastore.data
        .map { settings ->
            settings[ONBOARDING_COMPLETED_KEY] ?: false
        }

    override suspend fun onOnboardingFinished() {
        context.datastore.edit { settings ->
            settings[ONBOARDING_COMPLETED_KEY] = true
        }
    }
}