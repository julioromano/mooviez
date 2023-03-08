package net.marcoromano.tmdb.trending

import net.marcoromano.tmdb.httpapi.TrendingMovies

internal data class TrendingState(
  val isLoading: Boolean = false,
  val errors: Map<String, Throwable> = emptyMap(),
  val movies: List<TrendingMovies.Movie> = emptyList(),
)
