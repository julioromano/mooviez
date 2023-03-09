package net.marcoromano.tmdb.movie

import net.marcoromano.tmdb.httpapi.Movie

internal data class MovieState(
  val isLoading: Boolean = false,
  val errors: Map<String, Throwable> = emptyMap(),
  val movie: Movie? = null,
)
