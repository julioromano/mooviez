package net.marcoromano.mooviez.trending.widgets.trending

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import app.cash.sqldelight.paging3.QueryPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import net.marcoromano.mooviez.database.Database
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.httpapi.HttpApi
import javax.inject.Inject

@HiltViewModel
internal class TrendingLazyVerticalGridViewModel @Inject constructor(
  httpApi: HttpApi,
  private val database: Database,
) : ViewModel() {
  @OptIn(ExperimentalPagingApi::class)
  val pager = Pager(
    config = PagingConfig(pageSize = 20), // 20 comes from tmdb api docs
    remoteMediator = Mediator(httpApi = httpApi, database = database),
  ) {
    QueryPagingSource(
      countQuery = database.movieQueries.countMovies(),
      transacter = database.movieQueries,
      context = Dispatchers.IO,
      queryProvider = database.movieQueries::movies,
    )
  }.flow
}

@OptIn(ExperimentalPagingApi::class)
private class Mediator(
  private val httpApi: HttpApi,
  private val database: Database,
) : RemoteMediator<Int, Movie>() {
  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, Movie>,
  ): MediatorResult {
    return try {
      val loadKey: Long? = when (loadType) {
        LoadType.REFRESH -> null
        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        LoadType.APPEND -> {
          val remoteKey = database.movieQueries.nextPage().executeAsOne()
          if (remoteKey.next_page == null) {
            return MediatorResult.Success(endOfPaginationReached = true)
          }
          remoteKey.next_page
        }
      }

      println("RemoteMediator.load:\nloadType: $loadType\nstate :$state\nloadKey: $loadKey")

      val movies = httpApi.trendingMovies(page = loadKey?.toInt() ?: 1)
      val nextKey = if (movies.page < movies.total_pages) movies.page + 1 else null

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
            insertNextPage(nextKey?.toLong())
          }
        }
      }

      MediatorResult.Success(endOfPaginationReached = nextKey == null)
    } catch (e: RuntimeException) {
      return MediatorResult.Error(e)
    }
  }
}
