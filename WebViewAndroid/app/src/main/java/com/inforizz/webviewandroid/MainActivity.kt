package com.inforizz.webviewandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inforizz.webviewandroid.core.data.datasource.FirestoreDataSource
import com.inforizz.webviewandroid.core.data.repository.FirestoreRepository
import com.inforizz.webviewandroid.core.domain.usercase.GetScreenStateUseCase
import com.inforizz.webviewandroid.core.presentation.screens.FlutterScreen
import com.inforizz.webviewandroid.core.presentation.screens.HomeScreen
import com.inforizz.webviewandroid.core.presentation.screens.WebViewScreen
import com.inforizz.webviewandroid.core.presentation.viewmodel.ScreenStateViewModel
import com.inforizz.webviewandroid.core.presentation.viewmodel.ScreenStateViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ScreenStateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // dependências
        val dataSource = FirestoreDataSource()
        val repository = FirestoreRepository(dataSource)
        val getScreenStateUseCase = GetScreenStateUseCase(repository)
        val viewModelFactory = ScreenStateViewModelFactory(getScreenStateUseCase)

        // Instanciando o ViewModel com o ViewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScreenStateViewModel::class.java)

        setContent {
            val navController = rememberNavController()
            val screenIsFlutter by viewModel.screenIsFlutter.collectAsState()

            NavHost(navController = navController, startDestination = if (screenIsFlutter) "flutter" else "home") {
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("flutter") {
                    FlutterScreen(screenIsFlutter)
                }
                composable("webview/{url}") { backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url") ?: ""
                    WebViewScreen(url = url)
                }
            }
        }
    }
}
