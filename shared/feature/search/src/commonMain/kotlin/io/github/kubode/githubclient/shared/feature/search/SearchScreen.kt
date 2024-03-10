package io.github.kubode.githubclient.shared.feature.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.watch
import io.github.kubode.githubclient.shared.feature.search.fragment.RepositoryFragment
import io.github.kubode.githubclient.shared.graphql.client.apolloClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

val LocalApolloClient = compositionLocalOf { apolloClient }

@Composable
fun SearchScreen() {
    val apolloClient = LocalApolloClient.current
    val viewModel = remember { RepositorySearchViewModel(apolloClient) }
    Search(viewModel.uiState)
}

@Composable
internal fun Search(uiState: SearchUiState) {
    Scaffold(
        topBar = {},
    ) {
        // TODO: Paging
        val state = rememberLazyListState()
        LaunchedEffect(Unit) {
            snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull()?.offset }
                .collect { println("XX: $it") }
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = state,
        ) {
            items(uiState.repositories, key = { it.id }) {
                Repository(it)
            }
        }
    }
}

@Composable
internal fun Repository(repositoryFragment: RepositoryFragment) {
    Card(
        backgroundColor = Color.DarkGray,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = repositoryFragment.nameWithOwner)
            Text(text = repositoryFragment.description.orEmpty(), fontSize = 14.sp)
            Text(text = repositoryFragment.primaryLanguage?.name.orEmpty())
            Text(text = "⭐️ ${repositoryFragment.stargazerCount}")
        }
    }
}

data class SearchUiState(
    val isLoading: Boolean = false,
    val repositories: List<RepositoryFragment> = emptyList(),
)

class RepositorySearchViewModel(
    private val apolloClient: ApolloClient,
    private val scope: CoroutineScope = MainScope(),
) {
    var uiState: SearchUiState by mutableStateOf(SearchUiState())
        private set

    init {
        scope.launch {
            apolloClient.query(SearchRepositoriesQuery("Kotlin"))
                .watch()
                .collect { data ->
                    uiState = uiState.copy(
                        isLoading = false,
                        repositories = uiState.repositories + data.dataAssertNoErrors.search
                            .edgesFilterNotNull()
                            ?.mapNotNull { it.node?.repositoryFragment }
                            .orEmpty()
                    )
                }
        }
    }
}
