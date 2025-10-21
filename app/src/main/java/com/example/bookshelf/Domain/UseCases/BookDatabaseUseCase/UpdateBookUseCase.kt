package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase

import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(
    private val bookDataBaseRepository: BookDataBaseRepository
){
    suspend operator fun invoke(bookEntity: BookEntity){
        bookDataBaseRepository.updateBook(bookEntity)
    }
}