package com.github.kubode.githubclient.shared.umbrella

import androidx.compose.runtime.Composable
import com.github.kubode.githubclient.shared.core.designsystem.GitHubClientTheme
import io.github.kubode.githubclient.shared.feature.search.SearchScreen

@Composable
fun GitHubClientApp() {
    // TODO: Navigation
    GitHubClientTheme {
        SearchScreen()
    }
}
