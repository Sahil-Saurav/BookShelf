package com.example.bookshelf.Domain.UseCases.SignOutUseCase

import android.util.Log
import com.example.bookshelf.Domain.Repository.AuthRepository

class SignOutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(){
        repository.signOut()
        Log.i("signout","use case Signout")
    }
}