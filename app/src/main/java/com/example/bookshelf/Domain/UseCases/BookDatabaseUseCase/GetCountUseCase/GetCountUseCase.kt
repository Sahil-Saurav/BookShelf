package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase

data class GetCountUseCase(
    val getCountFinishedBookUseCase: GetCountFinishedBookUseCase,
    val getCountCurrentlyReadingBookUseCase: GetCountCurrentlyReadingBookUseCase,
    val getCountNotStartedBookUseCase: GetCountNotStartedBookUseCase
)
