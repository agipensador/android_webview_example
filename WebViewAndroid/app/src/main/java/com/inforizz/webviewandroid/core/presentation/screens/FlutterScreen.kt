import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
@ExperimentalMaterial3Api
@SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
fun FlutterScreen(url: String) {
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    val webView = remember {
        WebView(context).apply {
            webViewClient = WebViewClient()
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    isLoading = newProgress < 100
                }
            }
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(url)
        }
    }

    Scaffold(
        topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        Toast.makeText(
                            context, "Não há páginas para voltar", Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            },

            title = { Text(text = "Android Webview > Flutter:", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(
                    parseColor(
                        "#000000"
                    )
                )
            )

        )
    }, content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = { webView }, modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    })

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