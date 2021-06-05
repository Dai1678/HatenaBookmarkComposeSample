package dev.dai.hatena_bookmark.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FeedScreen(
  feedCategoryName: String,
  feedViewModel: FeedViewModel = viewModel()
) {
  val uiState by feedViewModel.uiState.collectAsState()
  feedViewModel.fetchFeedList(feedCategoryName)

  when {
    uiState.error != null -> {
    }
    else -> {
      FeedList(feedList = uiState.feedList, uiState.loading) {
        feedViewModel.refresh(feedCategoryName)
      }
    }
  }
}
