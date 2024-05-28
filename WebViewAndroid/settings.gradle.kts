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
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("flutter_module/build/host/outputs/repo")
        }
        maven {
            url = uri("https://storage.googleapis.com/download.flutter.io")
        }
        // ... adicione outros repositórios conforme necessário ...
    }
}


rootProject.name = "WebView Android"
include(":app")
