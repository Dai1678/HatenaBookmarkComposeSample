package dev.dai.hatena_bookmark.repository.mapper

import dev.dai.hatena_bookmark.api.xml.FeedItemXml
import dev.dai.hatena_bookmark.api.xml.FeedXml
import dev.dai.hatena_bookmark.model.Feed

fun FeedXml.toFeedList(): List<Feed> {
  val feedList = arrayListOf<Feed>()
  feeds.forEach {
    feedList.add(it.toFeed())
  }
  return feedList
}

private fun FeedItemXml.toFeed(): Feed {
  return Feed(
    title = title,
    link = link,
    description = description,
    bookmarkCount = bookmarkCount,
    imageUrl = imageUrl
  )
}
