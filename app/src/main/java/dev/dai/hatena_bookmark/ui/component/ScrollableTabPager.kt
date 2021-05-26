package dev.dai.hatena_bookmark.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScrollableTabPager(
  pageTitleList: List<String>,
  content: @Composable ColumnScope.(page: Int) -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  val pagerState = rememberPagerState(pageCount = pageTitleList.size)

  Column(Modifier.fillMaxSize()) {
    ScrollableTabRow(
      selectedTabIndex = pagerState.currentPage,
      backgroundColor = MaterialTheme.colors.surface,
      contentColor = MaterialTheme.colors.primary,
      indicator = { tabPositions ->
        TabRowDefaults.Indicator(
          Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
      }
    ) {
      pageTitleList.forEachIndexed { index, title ->
        Tab(
          text = { Text(title) },
          selected = pagerState.currentPage == index,
          onClick = {
            coroutineScope.launch {
              pagerState.animateScrollToPage(index)
            }
          },
        )
      }
    }

    HorizontalPager(state = pagerState) { page ->
      content(this@Column, page)
    }
  }
}
