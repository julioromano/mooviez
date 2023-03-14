package net.marcoromano.mooviez.httpapi

public enum class MediaType(private val mediaType: String) {
  ALL("all"),
  MOVIE("movie"),
  TV("tv"),
  PERSON("person"),
  ;

  override fun toString(): String = mediaType
}
