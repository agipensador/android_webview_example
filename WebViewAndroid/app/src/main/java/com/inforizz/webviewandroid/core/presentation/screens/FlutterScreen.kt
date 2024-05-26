package com.inforizz.webviewandroid.core.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FlutterScreen(screenIsFlutter: Boolean) {
    Text(text = "Tela do Flutter? $screenIsFlutter")
}