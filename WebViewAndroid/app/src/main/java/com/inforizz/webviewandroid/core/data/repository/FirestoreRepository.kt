package com.inforizz.webviewandroid.core.data.repository

import com.inforizz.webviewandroid.core.data.datasource.FirestoreDataSource
import com.inforizz.webviewandroid.core.domain.repository.ScreenStateRepository

class FirestoreRepository(private val dataSource: FirestoreDataSource) : ScreenStateRepository {
    override suspend fun getScreenState(): Boolean {
        return dataSource.getScreenState()
    }
}