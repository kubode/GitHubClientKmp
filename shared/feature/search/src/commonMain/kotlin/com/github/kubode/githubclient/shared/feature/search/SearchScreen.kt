package com.github.kubode.githubclient.shared.feature.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.watch

@Composable
fun SearchScreen(viewModel: RepositorySearchViewModel) {
    Search(viewModel.uiState)
}

@Composable
internal fun Search(uiState: SearchUiState) {
    Scaffold(
        topBar = {},
    ) {
    }
}

data class SearchUiState(
    val isLoading: Boolean = false,
)

class RepositorySearchViewModel(
    private val apolloClient: ApolloClient,
) {
    var uiState: SearchUiState by mutableStateOf(SearchUiState())
        private set

    fun initialLoad() {
        // TODO
        apolloClient.query(SearchRepositoriesQuery("Kotlin"))
            .watch()
    }
}
