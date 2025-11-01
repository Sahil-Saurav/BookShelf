package com.example.bookshelf.Presentation.HomeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase.GetCountUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCurrentlyReadingBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountUseCase: GetCountUseCase,
    private val getCurrentlyReadingBookUseCase: GetCurrentlyReadingBookUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomeScreenState>(HomeScreenState())
    val state : State<HomeScreenState> = _state

    init {
        initialiseDashBoard()
    }

    fun initialiseDashBoard() {
        _state.value = HomeScreenState(isLoading = true)

        val bookFlow = getCurrentlyReadingBookUseCase()
        val currentlyReadingFlow = getCountUseCase.getCountCurrentlyReadingBookUseCase()
        val finishedFlow = getCountUseCase.getCountFinishedBookUseCase()
        val notStartedFlow = getCountUseCase.getCountNotStartedBookUseCase()

        viewModelScope.launch {
            combine(bookFlow, currentlyReadingFlow, finishedFlow, notStartedFlow) { book, current, finished, notStarted ->
                HomeScreenState(
                    book = book,
                    currentlyReading = current,
                    notStarted = notStarted,
                    finished = finished,
                    isLoading = false,
                    total = current+notStarted+finished
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }
}