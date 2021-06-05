package dev.dai.hatena_bookmark.api

import dev.dai.hatena_bookmark.api.xml.FeedXml
import retrofit2.http.GET
import retrofit2.http.Path

interface HatenaBookmarkApi {
  @GET("hotentry/{category_name}.rss")
  suspend fun getFeeds(
    @Path("category_name") categoryName: String
  ): FeedXml
}
