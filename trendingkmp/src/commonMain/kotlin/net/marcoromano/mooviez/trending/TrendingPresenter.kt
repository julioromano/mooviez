package net.marcoromano.mooviez.trending

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.httpapi.HttpApi

@Inject
public class TrendingPresenter(
  private val httpApi: HttpApi,
  private val database: Database,
  private val globalScope: CoroutineScope,
) : Presenter<TrendingState> {
  @Composable
  override fun present(): TrendingState {

    val movies by database.movieQueries
      .movies(limit = 100, offset = 0)
      .asFlow()
      .mapToList(Dispatchers.IO)
      .collectAsState(emptyList())

    return TrendingState(
      movies = movies.toImmutableList(),
      eventSink = {
        when (it) {
          is TrendingEvent.Refresh -> refresh()
          is TrendingEvent.NavToDetails -> TODO()
        }
      },
    )
  }

  private fun refresh() {
    globalScope.launch {
      updateMoviesInDbFromHttp()
    }
  }

  private suspend fun updateMoviesInDbFromHttp() {
    val movies = httpApi.trendingMovies(page = 1)
    database.movieQueries.apply {
      transaction {
        movies.results.forEachIndexed { i, movie ->
          insertMovie(
            Movie(
              position = i.toLong(),
              id = movie.id,
              title = movie.title,
              poster_path = movie.poster_path,
              overview = movie.overview,
              vote_average = movie.vote_average,
              release_date = movie.release_date,
            ),
          )
          // insertNextPage(nextPage?.toLong())
        }
      }
    }
  }
}
