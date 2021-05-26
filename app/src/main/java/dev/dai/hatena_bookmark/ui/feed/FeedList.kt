package dev.dai.hatena_bookmark.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import dev.dai.hatena_bookmark.R
import dev.dai.hatena_bookmark.model.Feed
import dev.dai.hatena_bookmark.ui.theme.HatenaBookmarkComposeSampleTheme

@Composable
fun FeedList(feedList: List<Feed>) {
  LazyColumn {
    items(feedList) { feed ->
      FeedItem(feed, feedList.first() == feed)
      Divider()
    }
  }
}

@Composable
fun FeedItem(feed: Feed, isTopFeed: Boolean) {
  val imageSize = if (isTopFeed) 128.dp else 96.dp
  Row(
    Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    Column(Modifier.weight(3f)) {
      Text(
        text = feed.title,
        fontWeight = if (isTopFeed) FontWeight.Bold else null,
        style = MaterialTheme.typography.subtitle1
      )
      Spacer(modifier = Modifier.height(8.dp))
      if (isTopFeed) {
        Text(
          text = feed.description,
          color = LocalContentColor.current.copy(alpha = 0.3f),
          style = MaterialTheme.typography.body2,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
      }
      Text(
        text = "${feed.bookmarkCount} users",
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2
      )
    }
    Spacer(modifier = Modifier.width(16.dp))
    Image(
      painter = rememberCoilPainter(
        request = feed.imageUrl,
        previewPlaceholder = R.drawable.ic_outline_panorama
      ),
      contentDescription = null,
      modifier = Modifier.size(imageSize),
      contentScale = ContentScale.Crop
    )
  }
}

@Preview(showBackground = true)
@Composable
fun FeedItemPreview() {
  HatenaBookmarkComposeSampleTheme {
    FeedItem(
      feed = Feed(
        title = "世の中に溢れる「うざい広告」をプロが徹底解説！マーケターは必見です | 株式会社LIG",
        link = "https://liginc.co.jp/543590",
        description = "みなさんこんにちは、LIGのマーケターのまこりーぬ（@makosaito214）です。 ネットサーフィンをしていると頻繁に出会う「うざい広告」ってありますよね。広告を制作、運用する立場としてこの手の広告がなぜ存在するのか、そして今後こういった広告はどうなっていくのかを、今回はしっかり勉強したいと思います。 今回講..",
        bookmarkCount = 735,
        imageUrl = "https://liginc.co.jp/wp-content/uploads/2021/04/49869f429542dad03d0f864f1d6c63aa.jpg"
      ),
      isTopFeed = true
    )
  }
}
