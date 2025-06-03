package com.example.bookshelf.Presentation.SearchScreen

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
class SearchScreenViewModel @Inject constructor(
    private val getBookByNameUseCase: GetBookByNameUseCase
) : ViewModel(){

    private val _state = mutableStateOf(SearchState())
    val state : State<SearchState> = _state

    fun getBookByName(bookName: String){
        getBookByNameUseCase(bookName = bookName).onEach{ result ->
            when(result){
                is Resource.Error ->{
                    _state.value = SearchState(error = result.message.toString())
                }
                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchState(books = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }

}