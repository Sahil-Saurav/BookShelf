package com.example.bookshelf.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.Presentation.HomeScreen.HomeScreen
import com.example.bookshelf.Presentation.Onboarding.OnBoardingViewModel
import com.example.bookshelf.Presentation.Onboarding.OnboardingPage
import com.example.bookshelf.Presentation.ui.theme.BookShelfTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{
            mainViewModel.isLoading.value
        }
        enableEdgeToEdge()
        setContent {
            BookShelfTheme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val startDestination = mainViewModel.startDestination.collectAsState()
                    if (startDestination.value != null) {
                        Navigation(startDestination = startDestination.value!!)
                    }
                }
            }
        }
    }
}