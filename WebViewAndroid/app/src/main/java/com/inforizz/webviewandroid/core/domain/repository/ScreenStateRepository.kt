package com.inforizz.webviewandroid.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ScreenStateRepository {
    fun observeScreenState(): Flow<Boolean>
}