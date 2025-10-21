package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase

import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val bookDataBaseRepository: BookDataBaseRepository
) {
    operator fun invoke() : Flow<List<BookEntity>>{
        return bookDataBaseRepository.getAllBooks()
    }
}