package com.example.bookshelf.Domain.UseCases.FireStoreUseCases

import android.util.Log
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.FireStoreRepository
import javax.inject.Inject

class deleteBookToFireStoreUseCase @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(uid: String,bookEntity: BookEntity){
        fireStoreRepository.deleteBook(uid,bookEntity)
        Log.i("deleteBook","delete use case called")
    }
}