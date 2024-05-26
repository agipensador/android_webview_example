package com.inforizz.webviewandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.inforizz.webviewandroid.core.presentation.screens.FlutterScreen
import com.inforizz.webviewandroid.core.presentation.screens.HomeScreen
import com.inforizz.webviewandroid.core.presentation.viewmodel.ScreenStateViewModel


class MainActivity : ComponentActivity() {
    private val viewModel: ScreenStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val screenIsFlutter by viewModel.screenIsFlutter.collectAsState()

            if (screenIsFlutter) {
                FlutterScreen(screenIsFlutter)
            } else {
                HomeScreen()
            }
        }
    }
}