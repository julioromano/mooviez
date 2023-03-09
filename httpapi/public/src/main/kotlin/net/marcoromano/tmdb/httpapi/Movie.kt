package net.marcoromano.tmdb.httpapi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class Movie(
  val id: Long,
  val title: String,
  val poster_path: String,
  val backdrop_path: String,
  val overview: String,
  val vote_average: Double,
  val popularity: Double,
  val runtime: Int,
  val release_date: String,
)
