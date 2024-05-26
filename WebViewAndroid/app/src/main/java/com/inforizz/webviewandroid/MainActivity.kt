@file:Suppress("DEPRECATION")

package com.inforizz.webviewandroid

import WebViewScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.inforizz.webviewandroid.core.data.datasource.FirestoreDataSource
import com.inforizz.webviewandroid.core.data.repository.FirestoreRepository
import com.inforizz.webviewandroid.core.domain.usercase.GetScreenStateUseCase
import com.inforizz.webviewandroid.core.presentation.screens.FlutterScreen
import com.inforizz.webviewandroid.core.presentation.screens.HomeScreen
import com.inforizz.webviewandroid.core.presentation.viewmodel.ScreenStateViewModel
import com.inforizz.webviewandroid.core.presentation.viewmodel.ScreenStateViewModelFactory

@ExperimentalMaterial3Api
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

            NavHost(
                navController = navController,
                startDestination = if (screenIsFlutter) "flutter" else "home"
            ) {
                composable(route = "home") {
                    HomeScreen(navController = navController)
                }
                composable(route = "flutter") {
                    FlutterScreen(screenIsFlutter)
                }
                composable(
                    route = "webView/{url}/{color}",
                    arguments = listOf(
                        navArgument("url") { type = NavType.StringType },
                        navArgument("color") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url") ?: ""
                    val colorHex = backStackEntry.arguments?.getString("color") ?: "#FFFFFF"

                    WebViewScreen(navController = navController, url = url, colorHex = colorHex)

                }
            }
        }
    }
}
