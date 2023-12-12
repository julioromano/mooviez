package net.marcoromano.mooviez.trending

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import net.marcoromano.mooviez.database.Movie

public data object TrendingScreen : Screen

public data class TrendingState(
  val movies: ImmutableList<Movie>,
  val eventSink: (TrendingEvent) -> Unit,
) : CircuitUiState

public sealed interface TrendingEvent : CircuitUiEvent {
  public data object Refresh : TrendingEvent
  public data class NavToDetails(val id: Long) : TrendingEvent
}

public fun aTrendingState(): TrendingState = TrendingState(
  movies = persistentListOf(
    Movie(
      position = 0,
      id = 0,
      title = "A very long title that must be wrapped in multiple lines",
      poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
      overview = "Once upon a time...",
      vote_average = 1.2,
      release_date = "2022-02-03",
    ),
    Movie(
      position = 0,
      id = 0,
      title = "A very long title that must be wrapped in multiple lines",
      poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
      overview = "Once upon a time...",
      vote_average = 1.2,
      release_date = "2022-02-03",
    ),
  ),
  eventSink = {},
)
