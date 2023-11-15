package net.marcoromano.mooviez.trending

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.httpapi.HttpApi
import kotlin.coroutines.CoroutineContext

public data class TrendingState(
  val movies: List<Movie>,
)

public fun aTrendingState(): TrendingState = TrendingState(
  movies = listOf(
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
)

@Inject
public class TrendingViewModel(
  private val httpApi: HttpApi,
  private val database: Database,
) {

  public fun movies(coroutineContext: CoroutineContext): Flow<List<Movie>> = database.movieQueries
    .movies(limit = 4769, offset = 0)
    .asFlow()
    .mapToList(coroutineContext)

  private suspend fun updateMoviesInDbFromHttp() {
    val movies = httpApi.trendingMovies(page = 1)
    database.movieQueries.apply {
      transaction {
        movies.results.forEachIndexed { i, movie ->
          insertMovie(
            Movie(
              position = (movies.page - 1) * 20L + i,
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
