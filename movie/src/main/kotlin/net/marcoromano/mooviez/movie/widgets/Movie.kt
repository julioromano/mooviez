package net.marcoromano.mooviez.movie.widgets

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import net.marcoromano.mooviez.movie.MovieState
import net.marcoromano.mooviez.movie.R
import net.marcoromano.mooviez.movie.demoMovieStateMovie

@Composable
internal fun Movie(
  modifier: Modifier = Modifier,
  movie: MovieState.Movie,
) {
  Column(
    modifier = modifier,
  ) {
    Column(
      modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Text(
        text = movie.subtitle,
        style = MaterialTheme.typography.titleSmall,
      )
      Text(
        text = movie.tagline,
        modifier = Modifier.alpha(0.7f),
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp,
      )
    }
    Box(
      contentAlignment = Alignment.Center,
    ) {
      val contentScale = ContentScale.Crop
      Image(
        painter = rememberAsyncImagePainter(
          model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w1280/${movie.backdropPath}")
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
            .data("https://image.tmdb.org/t/p/w780/${movie.posterPath}")
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
      Spacer(modifier = Modifier.size(4.dp))
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        UserScore(userScore = movie.userScore)
        Text(
          text = stringResource(R.string.user_score),
          fontWeight = FontWeight.Bold,
        )
      }
      Spacer(modifier = Modifier.size(4.dp))
      Text(
        text = stringResource(R.string.overview),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall,
      )
      Text(text = movie.overview)
    }
  }
}

@Preview(widthDp = 1000, heightDp = 500)
@Composable
private fun Preview() {
  Movie(
    modifier = Modifier.fillMaxSize(),
    movie = demoMovieStateMovie(),
  )
}
