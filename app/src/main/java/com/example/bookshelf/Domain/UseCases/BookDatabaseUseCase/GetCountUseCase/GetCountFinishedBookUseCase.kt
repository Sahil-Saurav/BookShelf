package com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase

import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountFinishedBookUseCase @Inject constructor(
    private val dataBaseRepository: BookDataBaseRepository
) {
    operator fun invoke():Flow<Int>{
        return dataBaseRepository.getCountOfFinishedBook()
    }
}