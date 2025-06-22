package com.example.bookshelf.Presentation.TestApi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Domain.UseCases.GetBookByNameAndAuthorUseCase.GetBookByNameAndAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class TestApiViewModel @Inject constructor(
    private val getBookByNameAndAuthorUseCase: GetBookByNameAndAuthorUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BookListState())
    val state: State<BookListState> = _state

    fun searchBookByNameAndAuthor(bookName: String, authorName: String) {
//        getBookByNameAndAuthorUseCase(bookName, authorName).getBookByNameAndAuthorUseCase.invoke(
//            bookName,
//            authorName
//        ).launchIn(viewModelScope)
    }
}
