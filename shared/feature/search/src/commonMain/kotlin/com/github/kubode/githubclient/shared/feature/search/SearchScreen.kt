package com.github.kubode.githubclient.shared.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.watch
import com.github.kubode.githubclient.shared.feature.search.fragment.RepositoryFragment
import com.github.kubode.githubclient.shared.graphql.client.apolloClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

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
            item {
                Box(
                    modifier = Modifier
                        .padding(32.dp)
                        .size(64.dp)
                        .background(Color.White)
                        .shadow(elevation = 4.dp, shape = CircleShape)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .padding(32.dp)
                        .size(64.dp)
                        .background(Color.White)
                        .clip(shape = CircleShape)
                        .drawWithCache {
                            val outline = CircleShape.createOutline(size, layoutDirection, this)
                            val paint = Paint().apply {
                                asFrameworkPaint().maskFilter =
                                    MaskFilter.makeBlur(FilterBlurMode.OUTER, 4f)
                            }
                            onDrawBehind { drawIntoCanvas { it.drawOutline(outline, paint) } }
                        }
                )
            }
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
