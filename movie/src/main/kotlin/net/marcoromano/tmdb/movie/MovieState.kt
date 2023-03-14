package net.marcoromano.mooviez.movie

import net.marcoromano.mooviez.httpapi.Movie

internal data class MovieState(
  val isLoading: Boolean = false,
  val errors: Map<String, Throwable> = emptyMap(),
  val movie: Movie? = null,
)
