package net.marcoromano.mooviez.movie

import androidx.compose.ui.text.AnnotatedString
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

public data class MovieScreen(
  val movieId: Long,
) : Screen

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

public sealed interface MovieEvent : CircuitUiEvent {
  public data object NavBack : MovieEvent
}
