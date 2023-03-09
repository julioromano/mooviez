package net.marcoromano.tmdb.movie.widgets

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import net.marcoromano.tmdb.httpapi.Movie
import net.marcoromano.tmdb.movie.demoMovie

@Composable
internal fun Movie(
  modifier: Modifier = Modifier,
  movie: Movie,
) {
  Column(
    modifier = modifier,
  ) {
    Box(
      contentAlignment = Alignment.Center,
    ) {
      val contentScale = ContentScale.Crop
      Image(
        painter = rememberAsyncImagePainter(
          model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w1280/${movie.backdrop_path}")
            .size(width = 1280, height = 720)
            .placeholder(ColorDrawable(Color.YELLOW)) // TODO: Make it better looking.
            .error(android.R.drawable.ic_dialog_alert) // TODO: Can be better looking.
            .crossfade(true)
            .build(),
          contentScale = contentScale,
        ),
        contentDescription = null,
        modifier = Modifier
          .height(340.dp)
          .fillMaxWidth()
          .alpha(0.5f),
        contentScale = contentScale,
      )
      Image(
        painter = rememberAsyncImagePainter(
          model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w780/${movie.poster_path}")
            .size(width = 780, height = 1170)
            .placeholder(ColorDrawable(Color.BLUE)) // TODO: Make it better looking.
            .error(android.R.drawable.ic_dialog_alert) // TODO: Can be better looking.
            .crossfade(true)
            .build(),
        ),
        contentDescription = null,
        modifier = Modifier
          .height(300.dp)
          .aspectRatio(780f / 1170f)
          .clip(RoundedCornerShape(32f)),
      )
    }
    Column(
      modifier = Modifier
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Text(text = "Release date: ${movie.release_date}")
      Text(text = "Runtime: ${movie.runtime}")
      Text(text = "User Score: ${(movie.vote_average * 10).toInt()}")
      Text(text = "Overview\n${movie.overview}")
      Text(text = "Popularity: ${movie.popularity}")
    }
  }
}

@Preview(widthDp = 1000, heightDp = 500)
@Composable
private fun Preview() {
  Movie(
    modifier = Modifier.fillMaxSize(),
    movie = demoMovie(),
  )
}
