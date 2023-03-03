package com.lixoten.demoviewmodel

import androidx.lifecycle.ViewModel
import com.lixoten.demoviewmodel.ui.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DemoViewModel: ViewModel() {
    val _uiState = MutableStateFlow<AppUiState>(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

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
    }
}