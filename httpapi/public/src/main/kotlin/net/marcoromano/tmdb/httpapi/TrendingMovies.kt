package net.marcoromano.mooviez.httpapi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class TrendingMovies(
  val page: Int,
  val total_pages: Int,
  val total_results: Int,
  val results: List<Movie>,
) {
  @JsonClass(generateAdapter = true)
  public data class Movie(
    val id: Long,
    val title: String,
    val poster_path: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String,
  )
}
