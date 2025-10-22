package com.example.bookshelf.Domain.UseCases.FireStoreUseCases

import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.FireStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getBookFromFireStoreUseCase @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(uid: String): Flow<List<BookEntity>>{
        return fireStoreRepository.getBook(uid)
    }
}