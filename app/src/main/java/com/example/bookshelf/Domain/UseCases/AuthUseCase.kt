package com.example.bookshelf.Domain.UseCases

import com.example.bookshelf.Domain.UseCases.GetCurrentUserIdUseCase.GetCurrentUserIdUseCase
import com.example.bookshelf.Domain.UseCases.SignInUseCase.SignInUseCase
import com.example.bookshelf.Domain.UseCases.SignOutUseCase.SignOutUseCase
import com.example.bookshelf.Domain.UseCases.SignUpUseCase.SignUpUseCase

data class AuthUseCase(
    val SignInUseCase: SignInUseCase,
    val SignUpUseCase: SignUpUseCase,
    val SignOutUseCase: SignOutUseCase,
    val GetCurrentUserIdUseCase: GetCurrentUserIdUseCase,
)
