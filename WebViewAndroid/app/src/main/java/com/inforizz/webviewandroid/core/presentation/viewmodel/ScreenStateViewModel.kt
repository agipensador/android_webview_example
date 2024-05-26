package com.inforizz.webviewandroid.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScreenStateViewModel(
//    private val getScreenStateUseCase: GetScreenStateUseCase
) : ViewModel() {
    private val _screenIsFlutter = MutableStateFlow(false)
    val screenIsFlutter: StateFlow<Boolean> get() = _screenIsFlutter

    init {
        fetchScreenState()
    }

    private fun fetchScreenState() {
//        viewModelScope.launch {
//            _screenIsFlutter.value = getScreenStateUseCase.execute()
//        }
    }
}
