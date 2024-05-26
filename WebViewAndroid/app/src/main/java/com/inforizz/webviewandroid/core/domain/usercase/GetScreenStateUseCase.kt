package com.inforizz.webviewandroid.core.domain.usercase

import com.inforizz.webviewandroid.core.domain.repository.ScreenStateRepository
import kotlinx.coroutines.flow.Flow

class GetScreenStateUseCase(private val repository: ScreenStateRepository) {
    fun execute(): Flow<Boolean> {
        return repository.observeScreenState()
    }
}