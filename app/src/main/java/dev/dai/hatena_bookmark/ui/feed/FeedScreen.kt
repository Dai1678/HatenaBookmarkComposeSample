package dev.dai.hatena_bookmark.ui.feed

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FeedScreen(
  feedCategoryName: String,
  scaffoldState: ScaffoldState = rememberScaffoldState(),
  feedViewModel: FeedViewModel = viewModel()
) {
  val uiState by feedViewModel.uiState.collectAsState()
  feedViewModel.fetchFeedList(feedCategoryName)

  when {
    uiState.hasError() -> {
      LaunchedEffect(scaffoldState.snackbarHostState) {
        val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
          message = uiState.error?.message ?: "unknown error",
          actionLabel = "再試行"
        )
        when (snackBarResult) {
          SnackbarResult.ActionPerformed -> feedViewModel.fetchFeedList(feedCategoryName)
          SnackbarResult.Dismissed -> feedViewModel.clearError()
        }
      }
    }
    else -> {
      FeedList(feedList = uiState.feedList, uiState.loading) {
        feedViewModel.refresh(feedCategoryName)
      }
    }
  }
}
