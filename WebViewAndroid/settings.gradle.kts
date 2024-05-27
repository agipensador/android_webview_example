pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    val storageUrl = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"

    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("/Users/gi/development/android_webview_example/webViewAndroid/flutter_module/build/host/outputs/repo")
        }
        maven {
            url = uri("$storageUrl/download.flutter.io")
        }
    }
}

rootProject.name = "WebView Android"
include(":app", ":flutter_module")