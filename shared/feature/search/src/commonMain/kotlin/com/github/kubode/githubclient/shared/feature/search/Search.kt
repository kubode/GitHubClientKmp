package com.github.kubode.githubclient.shared.feature.search

import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.watch

@Composable
fun RepositorySearchScreen(viewModel: RepositorySearchViewModel) {
    Text("Search")
}

class RepositorySearchViewModel(
    private val apolloClient: ApolloClient
) {
    var state: State by mutableStateOf(State())
        private set

    data class State(
        val pages: List<Page> = emptyList()
    ) {
        sealed class Page {
            object Loading : Page()
            data class Success(val data: SearchRepositoriesQuery.Data)
            data class Failure(val throwable: Throwable)
        }
    }

    fun initialLoad() {
        // TODO
        apolloClient.query(SearchRepositoriesQuery("Kotlin"))
            .watch()
    }
}
