package com.github.kubode.githubclient.shared.umbrella

import androidx.compose.runtime.Composable
import com.github.kubode.githubclient.shared.feature.search.RepositorySearchViewModel
import com.github.kubode.githubclient.shared.feature.search.SearchScreen
import com.github.kubode.githubclient.shared.graphql.client.apolloClient

@Composable
fun GitHubClientKmpApp() {
    GitHubClientKmpTheme {
        SearchScreen(viewModel = RepositorySearchViewModel(apolloClient))
    }
}
