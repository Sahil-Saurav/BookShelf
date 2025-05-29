package com.example.bookshelf.Domain.UseCases.GetBookByNameUseCase

import coil.network.HttpException
import com.example.bookshelf.Common.Httpdetails
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Data.Remote.SearchByNameDTO.toBookByBookName
import com.example.bookshelf.Domain.Models.BookByBookName
import com.example.bookshelf.Domain.Repository.BookShelfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetBookByNameUseCase @Inject constructor(
    private val bookShelfRepository : BookShelfRepository
) {
    operator fun invoke(bookName: String): Flow<Resource<List<BookByBookName>>> = flow {
        try {
            emit(Resource.Loading())
            val books = bookShelfRepository.getBookByName(bookName, Httpdetails.API_KEY)
                .map { it.toBookByBookName() }
            emit(Resource.Success(books))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Internet Connection Required"))
        }
    }
}