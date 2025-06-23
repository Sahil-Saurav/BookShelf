package com.example.bookshelf.Presentation.BookDetailsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Domain.UseCases.GetBookUseCase.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase
): ViewModel(){
    private val _state = mutableStateOf<BookScreenState>(BookScreenState())
    val state : State<BookScreenState> = _state

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
                }
            }
        }.launchIn(viewModelScope)
    }
}