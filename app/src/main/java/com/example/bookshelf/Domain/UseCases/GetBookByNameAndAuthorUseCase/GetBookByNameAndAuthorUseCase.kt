package com.example.bookshelf.Domain.UseCases.GetBookByNameAndAuthorUseCase

import android.util.Log
import coil.network.HttpException
import com.example.bookshelf.Common.Httpdetails
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Data.Remote.SearchByNameAndAuthorDTO.toBookByNameAndAuthor
import com.example.bookshelf.Domain.Models.BookByBookNameAndAuthor
import com.example.bookshelf.Domain.Repository.BookShelfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetBookByNameAndAuthorUseCase @Inject constructor(
    private val bookShelfRepository: BookShelfRepository
){
    operator fun invoke(bookName: String,authorName: String):Flow<Resource<BookByBookNameAndAuthor>> = flow {
            try {
                emit(Resource.Loading())
                val books = bookShelfRepository.getBookByNameAndAuthor(bookName,authorName,Httpdetails.API_KEY).toBookByNameAndAuthor()
                emit(Resource.Success(books))
                Log.i("Success",books.toString())
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage?:"An unknown Error Occurred"))
                Log.i("Error","inside Http Exception")
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage?:"Internet Require"))
                Log.i("Error","Inside IO Exception")
            }
    }
}