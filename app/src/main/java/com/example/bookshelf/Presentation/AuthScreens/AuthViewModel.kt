package com.example.bookshelf.Presentation.AuthScreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.AuthResult
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.FireStoreUseCases.addUserToFireStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val addUserToFireStore: addUserToFireStore
): ViewModel() {
    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Idle)
    val authState = _authState.asStateFlow()

    private val _currentUser = MutableStateFlow<String?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        checkAuthState()
    }

    private fun checkAuthState(){
        _currentUser.value = authUseCase.GetCurrentUserIdUseCase()
    }

    fun signUp(email:String,password:String){
        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            val result = authUseCase.SignUpUseCase(email, password)
            _authState.value = result
            if(result is AuthResult.Success){
                checkAuthState()
            }
        }
    }

    fun signIn(email:String,password:String){
        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            val result = authUseCase.SignInUseCase(email, password)
            _authState.value = result
            if(result is AuthResult.Success){
                checkAuthState()
                val uid = _currentUser.value

                if (uid != null) {
                    addUserToFireStore(uid)
                } else {
                    Log.e("AuthViewModel", "Sign-in successful but current user ID is null.")
                }
            }
        }
    }

    fun signOut(){
        viewModelScope.launch {
            authUseCase.SignOutUseCase()
            _authState.value = AuthResult.Idle
            checkAuthState()
            Log.i("signout","viewmodel signout")
        }
    }

    fun resetState(){
        _authState.value = AuthResult.Idle
    }
}