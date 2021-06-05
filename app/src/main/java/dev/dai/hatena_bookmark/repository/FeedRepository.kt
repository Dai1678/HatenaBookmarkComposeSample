package dev.dai.hatena_bookmark.repository

import dev.dai.hatena_bookmark.api.HatenaBookmarkApi
import dev.dai.hatena_bookmark.model.Feed
import dev.dai.hatena_bookmark.repository.mapper.toFeedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface FeedRepository {
  suspend fun feedList(categoryName: String): Flow<List<Feed>>
}

class FeedRepositoryImpl @Inject constructor(
  private val hatenaBookmarkApi: HatenaBookmarkApi
) : FeedRepository {
  override suspend fun feedList(categoryName: String): Flow<List<Feed>> = flow {
    val feedList = hatenaBookmarkApi.getFeeds(categoryName).toFeedList()
    emit(feedList)
  }.flowOn(Dispatchers.IO)
}
