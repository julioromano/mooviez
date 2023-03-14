package net.marcoromano.mooviez.movie

import androidx.compose.ui.text.AnnotatedString

internal data class MovieState(
  val isLoading: Boolean = false,
  val errors: Map<String, Throwable> = emptyMap(),
  val movie: Movie? = null,
) {
  data class Movie(
    val title: AnnotatedString,
    val subtitle: String,
    val tagline: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val userScore: Int,
    val releaseDate: String,
  )
}
