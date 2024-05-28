package com.inforizz.webviewandroid.core.presentation.screens.components


import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
@ExperimentalMaterial3Api
@SuppressLint("SetJavaScriptEnabled")
fun WebViewContent(
    url: String,
    onLoadingChange: (Boolean) -> Unit,
    onWebViewCreated: (WebView) -> Unit
) {
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val webView = remember {
        WebView(context).apply {
            webViewClient = WebViewClient()
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    onLoadingChange(newProgress < 100)
                }
            }
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(url)
        }
    }

    onWebViewCreated(webView)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize()
        )

        LaunchedEffect(url) {
            snapshotFlow { webView.canGoBack() }.collect { canGoBack = it }
            snapshotFlow { webView.canGoForward() }.collect { canGoForward = it }
        }

        DisposableEffect(Unit) {
            onDispose {
                webView.destroy()
            }
        }
    }
}
