package dev.dai.hatena_bookmark.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.dai.hatena_bookmark.model.FeedCategory

@Composable
fun FeedScreen(
  feedCategory: FeedCategory,
  feedViewModel: FeedViewModel = viewModel()
) {
  val uiState by feedViewModel.uiState.collectAsState()
  feedViewModel.fetchFeedList(feedCategory)

  when {
    uiState.error != null -> {

    }
    else -> {
      FeedList(feedList = uiState.feedList, uiState.loading) {
        feedViewModel.refresh(feedCategory)
      }
    }
  }
}
