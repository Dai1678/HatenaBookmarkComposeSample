package dev.dai.hatena_bookmark.model

data class Feed(
  val title: String,
  val link: String,
  val description: String,
  val imageUrl: String,
  val bookmarkCount: Int
)
