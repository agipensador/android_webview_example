package com.inforizz.webviewandroid.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inforizz.webviewandroid.core.domain.usercase.GetScreenStateUseCase


class ScreenStateViewModelFactory(private val getScreenStateUseCase: GetScreenStateUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScreenStateViewModel::class.java)) {
            return ScreenStateViewModel(getScreenStateUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}