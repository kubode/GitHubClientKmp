import androidx.compose.ui.window.Application
import com.github.kubode.githubclient.shared.feature.search.Search
import platform.UIKit.UIViewController

// This file is required because the header file will not be created if any source code does not exist on K/N.

fun provideApplication(): UIViewController {
    return Application("GitHub") {
        Search()
    }
}
