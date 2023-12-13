package net.marcoromano.mooviez.movie

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@Preview
@Composable
private fun Preview() {
  Movie(
    state = MovieState(
      movie = demoMovieStateMovie(),
      eventSink = {},
    ),
  )
}
