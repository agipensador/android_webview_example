package com.inforizz.webviewandroid.core.data.repository

import com.inforizz.webviewandroid.core.data.datasource.FirestoreDataSource
import com.inforizz.webviewandroid.core.domain.repository.ScreenStateRepository
import kotlinx.coroutines.flow.Flow

class FirestoreRepository(private val dataSource: FirestoreDataSource) : ScreenStateRepository {
    override fun observeScreenState(): Flow<Boolean> {
        return dataSource.observeScreenState()
    }
}