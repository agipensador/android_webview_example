package com.inforizz.webviewandroid.core.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreDataSource {
    private val firestore = FirebaseFirestore.getInstance()

    fun observeScreenState(): Flow<Boolean> = callbackFlow {
        val listener = firestore.collection("config").document("screenState")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }
                val screenIsFlutter = snapshot?.getBoolean("screenIsFlutter") ?: false
                trySend(screenIsFlutter)
            }
        awaitClose { listener.remove() }
    }

}