import androidx.compose.ui.window.ComposeUIViewController
import io.github.kubode.githubclient.shared.feature.search.SearchScreen
import platform.UIKit.UIViewController

// This file is required because the header file will not be created if any source code does not exist on K/N.

fun provideApplication(): UIViewController {
    return ComposeUIViewController {
        SearchScreen()
    }
}
