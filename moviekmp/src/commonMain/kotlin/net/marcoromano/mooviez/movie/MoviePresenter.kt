package net.marcoromano.mooviez.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.httpapi.HttpApi
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.UUID
import kotlin.math.roundToInt

public class MoviePresenter @Inject constructor(
  private val httpApi: HttpApi,
  private val database: Database,
  private val scope: CoroutineScope,
  @Assisted private val movieId: Long,
  @Assisted private val navigator: Navigator,
) : Presenter<MovieState> {


  @Composable
  override fun present(): MovieState {
    var isLoading by remember { mutableStateOf(false) }
    var errors by remember { mutableStateOf((emptyMap<String, Throwable>())) }
    var movie by remember { mutableStateOf<MovieState.Movie?>((null)) }

    fun load() {
      scope.launch {
        isLoading = true
        runCatching {
          httpApi.movie(movieId = movieId)
        }.onSuccess {
          val releaseDate = LocalDate.parse(it.release_date)
          val runtime = Duration.ofMinutes(it.runtime.toLong())
          movie = MovieState.Movie(
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
          isLoading = false
        }.onFailure {
          errors += (UUID.randomUUID().toString() to it)
          isLoading = false
        }
      }
    }

    LaunchedEffect(Unit) {
      load()
    }

    return MovieState(
      isLoading = false,
      errors = mapOf(),
      movie = null,
      eventSink = {
        when (it) {
          MovieEvent.NavBack -> navigator.pop()
        }
      },
    )
  }

//  internal fun consumeError(uuid: String) {
//    errors.update { it - uuid }
//  }
}
