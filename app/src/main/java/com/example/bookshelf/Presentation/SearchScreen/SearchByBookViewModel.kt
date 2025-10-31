package com.example.bookshelf.Presentation.SearchScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.FireStoreUseCases.getBookFromFireStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchByBookViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val getBookFromFireStoreUseCase : getBookFromFireStoreUseCase,
) : ViewModel() {
    private val _bookshelf = MutableStateFlow<List<BookEntity>>(emptyList())
    val bookshelf : StateFlow<List<BookEntity>> = _bookshelf.asStateFlow()

    private val _state = mutableStateOf<SearchByBookState>(SearchByBookState())
    val state : State<SearchByBookState> = _state

    private val _uid = authUseCase.GetCurrentUserIdUseCase()


     fun getBook(uid : String?){
        _state.value = SearchByBookState(isLoading = true)

        viewModelScope.launch {
            if(uid != null){
                getBookFromFireStoreUseCase(uid).collect{ book ->
                    _state.value = SearchByBookState(book = book)
                }
            }else{
                _state.value = SearchByBookState(error = "Your Book Collection is Empty!!")
            }
        }
    }

}