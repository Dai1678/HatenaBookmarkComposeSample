package dev.dai.hatena_bookmark.model

enum class FeedCategory(val categoryName: String) {
  ALL("総合"),
  SOCIAL("世の中"),
  ECONOMICS("政治と経済"),
  LIFE("暮らし");

  companion object {
    fun getCategoryNameList(): List<String> = values().map { it.categoryName }

    fun fromOrdinal(ordinal: Int): FeedCategory = values().first { it.ordinal == ordinal }
  }
}
