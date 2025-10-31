package com.example.bookshelf.Presentation.SearchScreen


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Domain.UseCases.GetBookByNameAndAuthorUseCase.GetBookByNameAndAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SearchAuthorViewModel @Inject constructor(
    private val getBookByNameAndAuthorUseCase: GetBookByNameAndAuthorUseCase
): ViewModel() {

    private val _state = mutableStateOf(SearchAuthorState())
    val state : State<SearchAuthorState> = _state

    val book = mutableStateOf("")
    val author = mutableStateOf("")

    fun setBookName(bookName: String){
        this.book.value = bookName
    }

    fun setAuthorName(authorName: String){
        this.author.value = authorName
    }

    fun getBookByAuthorName(authorName: String){
        getBookByNameAndAuthorUseCase(authorName).onEach{ result->
            when(result){
                is Resource.Error -> {
                    _state.value = SearchAuthorState(error = result.message.toString())
                }
                is Resource.Loading -> {
                    _state.value = SearchAuthorState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchAuthorState(books = result.data)
                    Log.i("Search Author",result.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}