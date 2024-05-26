package com.inforizz.webviewandroid.core.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreDataSource {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getScreenState(): Boolean {
        val snapshot = firestore.collection("config").document("screenState").get().await()
        return snapshot.getBoolean("screenIsFlutter") ?: false
    }
}