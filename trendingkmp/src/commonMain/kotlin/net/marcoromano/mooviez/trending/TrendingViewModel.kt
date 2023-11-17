package net.marcoromano.mooviez.trending

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.httpapi.HttpApi

@Inject
public class TrendingViewModel(
  private val httpApi: HttpApi,
  private val database: Database,
  private val globalScope: CoroutineScope,
) {
  public fun state(scope: CoroutineScope): StateFlow<TrendingState> =
    scope.launchMolecule(RecompositionMode.ContextClock) {

      val movies by database.movieQueries
        .movies(limit = 100, offset = 0)
        .asFlow()
        .mapToList(Dispatchers.IO)
        .collectAsState(emptyList())

      TrendingState(
        movies = movies.toImmutableList(),
      )
    }

  public fun refresh() {
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
