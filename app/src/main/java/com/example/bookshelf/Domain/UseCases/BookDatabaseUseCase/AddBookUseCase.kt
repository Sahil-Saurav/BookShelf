package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase

import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import javax.inject.Inject

class AddBookUseCase @Inject constructor(
    private val bookDataBaseRepository: BookDataBaseRepository
) {
    suspend operator fun invoke(bookEntity: BookEntity){
        bookDataBaseRepository.addBook(bookEntity)
    }
}