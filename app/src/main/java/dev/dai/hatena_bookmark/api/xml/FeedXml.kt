package dev.dai.hatena_bookmark.api.xml

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rdf:RDF", strict = false)
data class FeedXml(
  @ElementList(inline = true)
  val feedList: List<FeedItemXml>
)

@Root(name = "item", strict = false)
data class FeedItemXml(
  @Element
  val title: String,

  @Element
  val link: String,

  @Element
  val description: String,

  @Path("hatena/bookmarkcount")
  @Element
  val bookmarkCount: Int,

  @Path("hatena/imageurl")
  @Element
  val imageUrl: String
)
