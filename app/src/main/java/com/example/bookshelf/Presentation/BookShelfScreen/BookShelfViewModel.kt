package com.example.bookshelf.Presentation.BookShelfScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Filter
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetAllBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCurrentlyReadingBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetFinishedBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetNotStartedBookUseCase
import com.example.bookshelf.Domain.UseCases.FireStoreUseCases.deleteBookToFireStoreUseCase
import com.example.bookshelf.Domain.UseCases.FireStoreUseCases.getBookFromFireStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookShelfViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val getBookFromFireStoreUseCase : getBookFromFireStoreUseCase,
    private val deleteBookToFireStoreUseCase: deleteBookToFireStoreUseCase,
    private val getFinishedBookUseCase : GetFinishedBookUseCase,
    private val getCurrentlyReadingBookUseCase: GetCurrentlyReadingBookUseCase,
    private val getNotStartedBookUseCase: GetNotStartedBookUseCase,
    private val getAllBookUseCase: GetAllBookUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(BookShelfState())
    val state : State<BookShelfState> = _state

    private val _uid = authUseCase.GetCurrentUserIdUseCase()

    init {
        getBook(_uid)
    }

    fun getBook(uid : String?){
        _state.value = BookShelfState(isLoading = true)

        viewModelScope.launch {
            if(uid != null){
                getBookFromFireStoreUseCase(uid).collect{ book ->
                    _state.value = BookShelfState(book = book)
                }
            }else{
                _state.value = BookShelfState(error = "Your Book Collection is Empty!!")
            }
        }
    }

    fun getFinishedBook(){
        _state.value = BookShelfState(isLoading = true)

        viewModelScope.launch {
            getFinishedBookUseCase().collect{ book ->
                _state.value = BookShelfState(book = book, filter = Filter.FINISHED)
            }
        }
    }

    fun getAllBook(){
        _state.value = BookShelfState(isLoading = true)

        viewModelScope.launch {
            getAllBookUseCase().collect{ book ->
                _state.value = BookShelfState(book = book, filter = Filter.ALL)
            }
        }
    }

    fun getCurrentlyReadingBook(){
        _state.value = BookShelfState(isLoading = true)

        viewModelScope.launch {
            getCurrentlyReadingBookUseCase().collect{ book ->
                _state.value = BookShelfState(book = book, filter = Filter.CURRENTLY_READING)
            }
        }
    }

    fun getNotStartedBook(){
        _state.value = BookShelfState(isLoading = true)

        viewModelScope.launch {
            getNotStartedBookUseCase().collect{ book ->
                _state.value = BookShelfState(book = book, filter = Filter.NOT_STARTED)
            }
        }
    }

    fun deleteBook(bookEntity: BookEntity){
        viewModelScope.launch {
            if(_uid != null){
                deleteBookToFireStoreUseCase(_uid, bookEntity)
                Log.i("deleteBook","book function executed from viewmodel")
            } else {
                Log.w("deleteBook", "Cannot delete: UID is null.")
            }
        }
    }
}