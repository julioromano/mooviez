package net.marcoromano.mooviez.movie

import androidx.compose.ui.text.AnnotatedString
import net.marcoromano.mooviez.httpapi.Movie

internal fun demoMovie() = Movie(
  id = 261414,
  title = "Larry David: Curb Your Enthusiasm",
  poster_path = "/m9QE3XkVc9hjDgpRTVoMpTNtfOY.jpg",
  overview = "Mock documentary about Seinfeld writer Larry David featuring contributions from " +
    "his friends and colleagues. Larry makes a return to stand-up comedy and prepares to film " +
    "a television special for HBO.  This is the original special that gave birth to the " +
    "long-running award-winning HBO series.",
  vote_average = 7.6,
  runtime = 59,
  backdrop_path = "/jG8qyfFdfbxmvmYEYFUx64toKIp.jpg",
  popularity = 6.57,
  release_date = "1999-10-17",
  tagline = "The universe is so much bigger than you realize.",
  genres = listOf(
    Movie.Genre(
      id = 0,
      name = "Drama",
    ),
  ),
)

internal fun demoMovieStateMovie() = MovieState.Movie(
  title = AnnotatedString("Larry David: Curb Your Enthusiasm"),
  subtitle = "Some subtitle",
  posterPath = "/m9QE3XkVc9hjDgpRTVoMpTNtfOY.jpg",
  backdropPath = "/jG8qyfFdfbxmvmYEYFUx64toKIp.jpg",
  overview = "Mock documentary about Seinfeld writer Larry David featuring contributions from " +
    "his friends and colleagues. Larry makes a return to stand-up comedy and prepares to film " +
    "a television special for HBO.  This is the original special that gave birth to the " +
    "long-running award-winning HBO series.",
  userScore = 76,
  releaseDate = "1999-10-17",
  tagline = "The universe is so much bigger than you realize.",
)
