package net.marcoromano.mooviez.trending.widgets.trending

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.marcoromano.mooviez.database.Movie

@Composable
internal fun TrendingLazyVerticalGrid(
  columns: GridCells,
  modifier: Modifier,
  movies: List<Movie>,
  onMovieClick: (id: Long) -> Unit,
) {
  LazyVerticalGrid(
    columns = columns,
    modifier = modifier,
  ) {
    items(movies) { movie ->
      Movie(
        movie = movie,
        navToDetail = { onMovieClick(it) },
      )
    }
  }
}
