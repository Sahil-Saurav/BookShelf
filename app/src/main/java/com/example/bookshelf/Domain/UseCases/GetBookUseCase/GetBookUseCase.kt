package com.example.bookshelf.Domain.UseCases.GetBookUseCase

import coil.network.HttpException
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Data.Remote.SearchBook.toBook
import com.example.bookshelf.Domain.Models.Book
import com.example.bookshelf.Domain.Repository.BookShelfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private val bookShelfRepository: BookShelfRepository
){
    operator fun invoke(volumeId: String) : Flow<Resource<Book>> = flow {
        try {
            emit(Resource.Loading())
            val book = bookShelfRepository.getBook(volumeId).toBook()
            emit(Resource.Success(book))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?.toString()?:"An Error Occurred"))
        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?.toString()?:"Internet Access Required"))
        }
    }
}