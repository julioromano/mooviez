package net.marcoromano.mooviez.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import net.marcoromano.mooviez.httpapi.HttpApi
import net.marcoromano.mooviez.httpapi.Movie
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.UUID
import kotlin.math.roundToInt

internal class MoviePresenter(
  private val httpApi: HttpApi,
  private val id: Long,
) {
  @Composable
  fun present(): MovieState {

    var isLoading by remember { mutableStateOf(false) }
    var errors by remember { mutableStateOf(mapOf<String, Throwable>()) }

    val httpMovie = produceState<Movie?>(initialValue = null) {
      isLoading = true
      runCatching {
        httpApi.movie(movieId = id)
      }.onSuccess {
        value = it
        isLoading = false
      }.onFailure {
        errors += (UUID.randomUUID().toString() to it)
        isLoading = false
      }
    }

    val movie by remember {
      derivedStateOf {
        httpMovie.value?.let {
          val releaseDate = LocalDate.parse(it.release_date)
          val runtime = Duration.ofMinutes(it.runtime.toLong())
          MovieState.Movie(
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
        }
      }
    }

    return MovieState(isLoading = isLoading, errors = errors, movie = movie)
  }
}
