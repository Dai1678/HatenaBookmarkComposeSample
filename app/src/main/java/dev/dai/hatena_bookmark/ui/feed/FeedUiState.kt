package dev.dai.hatena_bookmark.ui.feed

import dev.dai.hatena_bookmark.model.Feed

data class FeedUiState(
  val feedList: List<Feed> = emptyList(),
  val loading: Boolean = false,
  val error: Throwable? = null
)
