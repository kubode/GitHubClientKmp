package com.github.kubode.githubclient.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.kubode.githubclient.shared.umbrella.GitHubClientKmpApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubClientKmpApp()
        }
    }
}
