package dev.dai.hatena_bookmark.api.xml

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

@NamespaceList(
  Namespace(reference = "http://purl.org/rss/1.0/"),
  Namespace(reference = "http://www.w3.org/1999/02/22-rdf-syntax-ns#", prefix = "rdf"),
  Namespace(reference = "http://purl.org/dc/elements/1.1/", prefix = "dc"),
  Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#", prefix = "hatena")
)
@Namespace(reference = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@Root(name = "RDF", strict = false)
data class FeedXml(
  @field:ElementList(name = "item", inline = true, required = false)
  var feeds: List<FeedItemXml>? = null
)

@Root(name = "item", strict = false)
data class FeedItemXml(
  @field:Element
  var title: String = "",

  @field:Element
  var link: String = "",

  @field:Element(required = false)
  var description: String? = "",

  @field:Element(name = "bookmarkcount")
  @Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#")
  var bookmarkCount: Int = 0,

  @field:Element(name = "imageurl", required = false)
  @Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#")
  var imageUrl: String? = ""
)
