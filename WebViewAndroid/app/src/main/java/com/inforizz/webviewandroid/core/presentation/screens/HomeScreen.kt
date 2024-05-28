package com.inforizz.webviewandroid.core.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inforizz.webviewandroid.core.presentation.model.ButtonData

@Composable
fun HomeScreen(navController: NavHostController) {

    val buttons = listOf(
        ButtonData(
            text = "Notícias",
            color = Color(0xFF800000),
            url = "previous-quince.surge.sh"
        ),
        ButtonData(
            text = "Ajude o RS",
            color = Color(0xFF800080),
            url = "https://emergencia.paraquemdoar.com.br/?ref=home_banner?utm_source=pirulito&utm_medium=home&utm_campaign=homeg1#doacao"
        ),
        ButtonData(
            text = "Futebol",
            color = Color(0xFF008015),
            url = "https://ge.globo.com/"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        buttons.forEach { buttonData ->
            Button(
                onClick = {
                    val url = Uri.encode(buttonData.url)
                    val color = Uri.encode(
                        "#${
                            buttonData.color.toArgb().toUInt().toString(16).padStart(8, '0')
                        }"
                    )
                    navController.navigate("webView/$url/$color")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonData.color)
            ) {
                Text(text = buttonData.text)
            }
        }
    }
}