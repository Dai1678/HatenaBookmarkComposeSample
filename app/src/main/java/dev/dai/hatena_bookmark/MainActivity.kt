package dev.dai.hatena_bookmark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.dai.hatena_bookmark.model.FeedCategory
import dev.dai.hatena_bookmark.ui.component.ScrollableTabPager
import dev.dai.hatena_bookmark.ui.feed.FeedScreen
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
      FeedScreen(feedCategoryName = FeedCategory.fromOrdinal(page).categoryName)
    }
  }
}
