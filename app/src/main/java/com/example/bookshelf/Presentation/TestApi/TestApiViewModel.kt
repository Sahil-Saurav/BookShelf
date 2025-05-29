package com.example.bookshelf.Presentation.TestApi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Domain.UseCases.GetBookByNameAndAuthorUseCase.GetBookByNameAndAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TestApiViewModel @Inject constructor(
    private val getBookByNameAndAuthorUseCase: GetBookByNameAndAuthorUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BookListState())
    val state: State<BookListState> = _state

    fun searchBookByNameAndAuthor(bookName: String, authorName: String) {
        getBookByNameAndAuthorUseCase(bookName, authorName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = BookListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = BookListState(books = result.data)
                }
                is Resource.Error -> {
                    _state.value = BookListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}
