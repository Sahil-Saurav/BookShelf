package com.example.bookshelf.Presentation.BookDetailsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.DoesBookExistUseCase
import com.example.bookshelf.Domain.UseCases.FireStoreUseCases.addBookToFireStoreUseCase
import com.example.bookshelf.Domain.UseCases.GetBookUseCase.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val addBookToFireStoreUseCase: addBookToFireStoreUseCase,
    private val doesBookExistUseCase: DoesBookExistUseCase,
    private val authUseCase: AuthUseCase
): ViewModel(){
    private val _state = mutableStateOf<BookScreenState>(BookScreenState())
    val state : State<BookScreenState> = _state
    private val _uid = authUseCase.GetCurrentUserIdUseCase()

    fun getBook(volumeId:String){
        getBookUseCase(volumeId).onEach{ result ->
            when(result){
                is Resource.Error -> {
                    _state.value = BookScreenState(error = result.message.toString())
                }
                is Resource.Loading -> {
                    _state.value = BookScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = BookScreenState(book = result.data)
                    observeBookExistence(volumeId)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addBook(bookEntity: BookEntity){
        viewModelScope.launch {
            addBookToFireStoreUseCase(_uid.toString(),bookEntity)
        }
    }

    private fun observeBookExistence(bookId: String) {
        doesBookExistUseCase(bookId).onEach { doesExist ->
            _state.value = _state.value.copy(doesBookExistInDatabase = doesExist)
        }.launchIn(viewModelScope)
    }
}