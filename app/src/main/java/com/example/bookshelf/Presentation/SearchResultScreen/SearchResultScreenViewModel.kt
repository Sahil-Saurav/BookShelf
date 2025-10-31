package com.example.bookshelf.Presentation.SearchResultScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Common.Resource
import com.example.bookshelf.Domain.UseCases.GetBookByNameUseCase.GetBookByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchResultScreenViewModel @Inject constructor(
    private val getBookByNameUseCase: GetBookByNameUseCase
) : ViewModel(){

    private val _state = mutableStateOf(SearchResultScreenState())
    val state : State<SearchResultScreenState> = _state

    fun getBookByName(bookName: String){
        getBookByNameUseCase(bookName = bookName).onEach{ result ->
            when(result){
                is Resource.Error ->{
                    _state.value = SearchResultScreenState(error = result.message.toString())
                }
                is Resource.Loading -> {
                    _state.value = SearchResultScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchResultScreenState(books = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }

}