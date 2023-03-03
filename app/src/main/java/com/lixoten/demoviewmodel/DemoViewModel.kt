package com.lixoten.demoviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lixoten.demoviewmodel.ui.AppUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DemoViewModel: ViewModel() {
    val _uiState = MutableStateFlow<AppUiState>(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    // what is eventFlow for? Events that are not really state..One time events like snackbar
    // one time event snackbar
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun updateFoo(foo: String) {
        _uiState.update {currentState ->
            currentState.copy(
                foo = foo
            )
        }
    }

    fun incrementCounter() {
        // notes - just to show we can use "it"
        //  - _uiState.update {currentState ->
        _uiState.update {
            it.copy(
                counter = _uiState.value.counter.inc()
            )
        }
        // one time event snackbar
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvent.ShowSnackbar(
                    message = "The current counter is: ${uiState.value.counter}"
                )
            )
        }
    }

    // one time event snackbar
    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        // object SaveNote: UiEvent()
    }
}