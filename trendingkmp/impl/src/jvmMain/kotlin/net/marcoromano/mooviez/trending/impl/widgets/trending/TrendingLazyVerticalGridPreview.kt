package net.marcoromano.mooviez.trending.impl.widgets.trending

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.marcoromano.mooviez.database.Movie

@Preview
@Composable
private fun TrendingLazyVerticalGridPreview() {
  TrendingLazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.fillMaxSize(),
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
    onMovieClick = {},
  )
}
