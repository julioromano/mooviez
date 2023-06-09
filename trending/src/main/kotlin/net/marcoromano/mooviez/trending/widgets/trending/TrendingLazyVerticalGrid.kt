package net.marcoromano.mooviez.trending.widgets.trending

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import net.marcoromano.mooviez.database.Movie

@Composable
internal fun TrendingLazyVerticalGrid(
  columns: GridCells,
  modifier: Modifier = Modifier,
  onMovieClick: (id: Long) -> Unit,
) {
  val vm = viewModel<TrendingLazyVerticalGridViewModel>()
  TrendingLazyVerticalGrid(
    columns,
    modifier,
    vm.pager,
    onMovieClick,
  )
}

@Composable
private fun TrendingLazyVerticalGrid(
  columns: GridCells,
  modifier: Modifier,
  pager: Flow<PagingData<Movie>>,
  onMovieClick: (id: Long) -> Unit,
) {
  val trendingPagingData = pager.collectAsLazyPagingItems()
  LazyVerticalGrid(
    columns = columns,
    modifier = modifier,
  ) {
    items(trendingPagingData.itemCount) { index ->
      val movie = trendingPagingData[index]
      if (movie != null) {
        Movie(
          movie = movie,
          navToDetail = { onMovieClick(it) },
        )
      } else {
        MoviePlaceHolder()
      }
    }
  }
}

@Preview
@Composable
private fun PreviewTrending() {
  TrendingLazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.fillMaxSize(),
    pager = flowOf(
      PagingData.from(
        listOf(
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
      ),
    ),
    onMovieClick = {},
  )
}
