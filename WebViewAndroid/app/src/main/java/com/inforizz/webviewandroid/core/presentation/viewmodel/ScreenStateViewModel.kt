package com.inforizz.webviewandroid.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforizz.webviewandroid.core.domain.usercase.GetScreenStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScreenStateViewModel(private val getScreenStateUseCase: GetScreenStateUseCase) : ViewModel() {
    private val _screenIsFlutter = MutableStateFlow(false)
    val screenIsFlutter: StateFlow<Boolean> get() = _screenIsFlutter

    init {
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModelScope.launch {
            getScreenStateUseCase.execute().collectLatest { screenIsFlutter ->
                _screenIsFlutter.value = screenIsFlutter
            }
        }
    }
}