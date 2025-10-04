package com.example.bookshelf.Domain.UseCases.SignUpUseCase

import com.example.bookshelf.Common.AuthResult
import com.example.bookshelf.Domain.Repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String) : AuthResult{
        if(email.isBlank() || password.isBlank()){
            return AuthResult.Error("email or password block can't be empty")
        }else{
            return repository.signUp(email,password)
        }
    }
}