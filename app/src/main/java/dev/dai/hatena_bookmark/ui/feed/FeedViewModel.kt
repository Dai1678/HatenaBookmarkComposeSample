package dev.dai.hatena_bookmark.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dai.hatena_bookmark.repository.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
  private val feedRepository: FeedRepository
) : ViewModel() {
  private val _uiState = MutableStateFlow(FeedUiState(loading = true))
  val uiState: StateFlow<FeedUiState> = _uiState

  fun fetchFeedList(categoryName: String) {
    viewModelScope.launch {
      feedRepository.feedList(categoryName).collect {
        _uiState.value = FeedUiState(feedList = it)
      }
    }
  }

  fun refresh(categoryName: String) {
    _uiState.value = _uiState.value.copy(loading = true)
    fetchFeedList(categoryName)
  }

  fun clearError() {
    _uiState.value = _uiState.value.copy(error = null)
  }
}
