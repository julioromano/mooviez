package net.marcoromano.tmdb.trending

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import net.marcoromano.tmdb.httpapi.TrendingMovies

@Composable
internal fun Movie(
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

@Preview(name = "Day mode")
@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
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
