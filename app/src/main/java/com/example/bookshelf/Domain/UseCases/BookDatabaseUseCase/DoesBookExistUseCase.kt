package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase

import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DoesBookExistUseCase @Inject constructor(
    private val bookDataBaseRepository: BookDataBaseRepository
) {
    operator fun invoke(bookID: String): Flow<Boolean>{
        return bookDataBaseRepository.doesBookExist(bookID)
    }
}