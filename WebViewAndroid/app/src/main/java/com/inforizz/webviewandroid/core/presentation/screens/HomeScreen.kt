package com.inforizz.webviewandroid.core.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /* Open WebView with URL */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
        ) {
            Text(text = "Notícias")
        }

        Button(
            onClick = { /* Open WebView with URL */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)

        ) {
            Text(text = "Ajude o RS")
        }

        Button(
            onClick = { /* Open WebView with URL */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(
                red = Color.Green.red * 0.7f,
                green = Color.Green.green * 0.7f,
                blue = Color.Green.blue * 0.7f
            )),
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
        ) {
            Text(text = "Futebol")
        }
    }
}
