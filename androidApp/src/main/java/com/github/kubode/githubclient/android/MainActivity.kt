package com.github.kubode.githubclient.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kubode.githubclient.android.ui.theme.GitHubKMMTheme
import com.github.kubode.githubclient.shared.feature.search.Greeting
import com.github.kubode.githubclient.shared.feature.search.RepositorySearchViewModel
import com.github.kubode.githubclient.shared.feature.search.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubKMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting(Greeting().greeting())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitHubKMMTheme {
        Greeting("Android")
    }
}
