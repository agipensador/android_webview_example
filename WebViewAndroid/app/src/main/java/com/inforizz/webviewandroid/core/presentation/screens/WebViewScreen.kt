import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
@ExperimentalMaterial3Api
@SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
fun WebViewScreen(navController: NavController, url: String, colorHex: String) {
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
                title = { Text(text = "Android WebView", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(
                        parseColor(
                            colorHex
                        )
                    )
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(parseColor(colorHex)),
                content = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        if (webView.canGoBack()) {
                            webView.goBack()
                        } else {
                            Toast.makeText(
                                context,
                                "Não há páginas para voltar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        if (webView.canGoForward()) {
                            webView.goForward()
                        } else {
                            Toast.makeText(context, "Não há páginas adiante", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Avançar",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { webView.reload() }) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Reload",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AndroidView(
                    factory = { webView },
                    modifier = Modifier.fillMaxSize()
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
        }
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