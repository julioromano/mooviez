package net.marcoromano.mooviez.movie

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.httpapi.HttpApi
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.UUID
import kotlin.math.roundToInt

@Inject
public class MovieViewModel(
  @Assisted private val savedStateHandle: SavedStateHandle,
  private val httpApi: HttpApi,
) : ViewModel() {
  private val isLoading = MutableStateFlow(false)
  private val errors = MutableStateFlow<Map<String, Throwable>>(emptyMap())
  private val movie = MutableStateFlow<MovieState.Movie?>(null)

  internal val state = combine(isLoading, errors, movie, ::MovieState)
    .stateIn(viewModelScope, SharingStarted.Lazily, MovieState())

  internal fun load() {
    viewModelScope.launch {
      isLoading.value = true
      runCatching {
        httpApi.movie(movieId = savedStateHandle["id"]!!)
      }.onSuccess {
        val releaseDate = LocalDate.parse(it.release_date)
        val runtime = Duration.ofMinutes(it.runtime.toLong())
        movie.value = MovieState.Movie(
          title = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
              append(it.title)
            }
            append(" (${releaseDate.year})")
          },
          subtitle = buildString {
            append(releaseDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)))
            append(" · ")
            it.genres.forEachIndexed { i, genre ->
              append(genre.name)
              if (i < it.genres.size - 1) append(", ")
            }
            append(" · ")
            append("${runtime.toHours()}h ${runtime.toMinutes() % 60}m")
          },
          tagline = it.tagline,
          posterPath = it.poster_path,
          backdropPath = it.backdrop_path,
          overview = it.overview,
          userScore = (it.vote_average * 10).roundToInt(),
          releaseDate = "$releaseDate",
        )
        isLoading.value = false
      }.onFailure { throwable ->
        errors.update {
          it + (UUID.randomUUID().toString() to throwable)
        }
        isLoading.value = false
      }
    }
  }

  internal fun consumeError(uuid: String) {
    errors.update { it - uuid }
  }
}
