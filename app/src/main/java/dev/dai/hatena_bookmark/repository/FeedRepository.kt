package dev.dai.hatena_bookmark.repository

import dev.dai.hatena_bookmark.model.Feed
import dev.dai.hatena_bookmark.model.FeedCategory

interface FeedRepository {
  suspend fun feedList(category: FeedCategory): List<Feed>
}

class FeedRepositoryImpl : FeedRepository {
  override suspend fun feedList(category: FeedCategory): List<Feed> {
    TODO("Not yet implemented")
  }
}
