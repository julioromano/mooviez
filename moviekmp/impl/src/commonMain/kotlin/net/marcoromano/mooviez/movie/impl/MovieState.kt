package net.marcoromano.mooviez.movie.impl

import androidx.compose.ui.text.AnnotatedString
import com.slack.circuit.runtime.CircuitUiState

public data class MovieState(
    val isLoading: Boolean = false,
    val errors: Map<String, Throwable> = emptyMap(),
    val movie: Movie? = null,
    val eventSink: (MovieEvent) -> Unit,
) : CircuitUiState {
  public data class Movie(
      val title: AnnotatedString,
      val subtitle: String,
      val tagline: String,
      val posterPath: String,
      val backdropPath: String?,
      val overview: String,
      val userScore: Int,
      val releaseDate: String,
  )
}
