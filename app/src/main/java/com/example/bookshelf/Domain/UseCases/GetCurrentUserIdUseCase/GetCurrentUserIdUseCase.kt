package com.example.bookshelf.Domain.UseCases.GetCurrentUserIdUseCase

import com.example.bookshelf.Domain.Repository.AuthRepository

class GetCurrentUserIdUseCase(private val repository:AuthRepository) {
    operator fun invoke(): String?{
        return repository.getCurrentUserId()
    }
}