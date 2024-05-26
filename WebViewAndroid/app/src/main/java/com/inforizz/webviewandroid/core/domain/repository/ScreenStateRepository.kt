package com.inforizz.webviewandroid.core.domain.repository

interface ScreenStateRepository {
    suspend fun getScreenState(): Boolean
}