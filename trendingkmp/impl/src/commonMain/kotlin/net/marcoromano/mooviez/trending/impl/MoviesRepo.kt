package net.marcoromano.mooviez.trending.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.httpapi.HttpApi

public interface MoviesRepo {
  public val movies: StateFlow<List<Movie>>
  public val isLoadingMore: StateFlow<Boolean>
  public fun loadMore()
}

public class MoviesRepoImpl @Inject constructor(
  private val httpApi: HttpApi,
  private val database: Database,
  private val scope: CoroutineScope,
) : MoviesRepo {

  override val movies: StateFlow<List<Movie>>
    get() = database.movieQueries
      .movies(limit = 100, offset = 0)
      .asFlow()
      .mapToList(Dispatchers.IO)
      .stateIn(
        scope = scope,
        started = SharingStarted.Lazily,
        initialValue = emptyList(),
      )

  private val _isLoadingMore = MutableStateFlow(false)
  override val isLoadingMore: StateFlow<Boolean>
    get() = _isLoadingMore

  public override fun loadMore() {
    scope.launch {
      _isLoadingMore.value = true
      updateMoviesInDbFromHttp()
      _isLoadingMore.value = false
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
