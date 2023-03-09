package net.marcoromano.tmdb.trending.widgets.trending

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import net.marcoromano.tmdb.httpapi.TrendingMovies

@Composable
internal fun TrendingLazyVerticalGrid(
  columns: GridCells,
  modifier: Modifier = Modifier,
  onMovieClick: (id: Long) -> Unit,
) {
  val vm = hiltViewModel<TrendingLazyVerticalGridViewModel>()
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
  pager: Flow<PagingData<TrendingMovies.Movie>>,
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
        Icon(
          imageVector = Icons.Default.Error,
          contentDescription = null,
        )
      }
    }
  }
}

@Composable
private fun Movie(
  movie: TrendingMovies.Movie,
  navToDetail: (id: Long) -> Unit,
) {
  Column(
    modifier = Modifier
      .padding(8.dp)
      .width(160.dp),
  ) {
    Box {
      Column {
        Image(
          painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
              .data("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
              .size(width = 500, height = 750)
              .placeholder(ColorDrawable(Color.BLUE)) // TODO: Make it better looking.
              .error(android.R.drawable.ic_dialog_alert) // TODO: Can be better looking.
              .crossfade(true)
              .build(),
          ),
          contentDescription = null,
          modifier = Modifier
            .aspectRatio(500f / 750f)
            .clip(RoundedCornerShape(32f))
            .clickable { navToDetail(movie.id) },
          contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.height(32.dp))
      }
      FloatingActionButton(
        onClick = {},
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(8.dp),
        shape = CircleShape,
      ) {
        Text(text = "${(movie.vote_average * 10).toInt()} %")
      }
    }
    Column(
      modifier = Modifier.padding(8.dp),
    ) {
      Text(
        text = movie.title,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        modifier = Modifier.alpha(0.5f),
        text = movie.release_date,
      )
    }
  }
}

@Preview
@Composable
private fun PreviewMovie() {
  Movie(
    movie = TrendingMovies.Movie(
      id = 0,
      title = "A very long title that must be wrapped in multiple lines",
      poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
      overview = "Once upon a time...",
      vote_average = 1.2,
      release_date = "2022-02-03",
    ),
    navToDetail = {},
  )
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
          TrendingMovies.Movie(
            id = 0,
            title = "A very long title that must be wrapped in multiple lines",
            poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
            overview = "Once upon a time...",
            vote_average = 1.2,
            release_date = "2022-02-03",
          ),
          TrendingMovies.Movie(
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
