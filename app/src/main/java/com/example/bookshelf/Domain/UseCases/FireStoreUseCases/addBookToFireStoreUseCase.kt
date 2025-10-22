package com.example.bookshelf.Domain.UseCases.FireStoreUseCases

import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.FireStoreRepository
import javax.inject.Inject

class addBookToFireStoreUseCase @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(uid: String,bookEntity: BookEntity){
        fireStoreRepository.addBook(uid,bookEntity)
    }
}