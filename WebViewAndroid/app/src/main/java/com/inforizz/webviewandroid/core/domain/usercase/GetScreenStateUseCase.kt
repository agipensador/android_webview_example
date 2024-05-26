package com.inforizz.webviewandroid.core.domain.usercase

import com.inforizz.webviewandroid.core.domain.repository.ScreenStateRepository


class GetScreenStateUseCase(private val repository: ScreenStateRepository) {
    suspend fun execute(): Boolean {
        return repository.getScreenState()
    }
}