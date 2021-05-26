package dev.dai.hatena_bookmark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.dai.hatena_bookmark.model.FeedCategory
import dev.dai.hatena_bookmark.ui.component.ScrollableTabPager
import dev.dai.hatena_bookmark.ui.theme.HatenaBookmarkComposeSampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HatenaBookmarkComposeSampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          AppContent()
        }
      }
    }
  }
}

@Composable
private fun AppContent() {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.primary
      )
    },
    modifier = Modifier.fillMaxSize()
  ) {
    ScrollableTabPager(pageTitleList = FeedCategory.getCategoryNameList()) { page ->
      Text(
        text = FeedCategory.fromOrdinal(page).categoryName,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  HatenaBookmarkComposeSampleTheme {
    Greeting("Android")
  }
}
