package net.marcoromano.tmdb.trending.widgets.trending

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.hilt.android.lifecycle.HiltViewModel
import net.marcoromano.tmdb.httpapi.HttpApi
import net.marcoromano.tmdb.httpapi.TrendingMovies
import javax.inject.Inject

@HiltViewModel
internal class TrendingLazyVerticalGridViewModel @Inject constructor(
  private val httpApi: HttpApi,
) : ViewModel() {
  val pager = Pager(PagingConfig(pageSize = 20)) { // 20 comes from tmdb api docs
    NetworkPagingSource(httpApi)
  }.flow
}

private class NetworkPagingSource(
  private val httpApi: HttpApi,
) : PagingSource<Int, TrendingMovies.Movie>() {
  override fun getRefreshKey(state: PagingState<Int, TrendingMovies.Movie>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingMovies.Movie> {
    return runCatching {
      httpApi.trendingMovies(page = params.key ?: 1)
    }.fold(
      {
        LoadResult.Page(
          data = it.results,
          prevKey = if (it.page > 1) it.page - 1 else null,
          nextKey = if (it.page < it.total_pages) it.page + 1 else null,
        )
      },
      {
        LoadResult.Error(it)
      },
    )
  }
}
