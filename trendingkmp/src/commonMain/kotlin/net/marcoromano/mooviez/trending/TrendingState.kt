package net.marcoromano.mooviez.trending

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import net.marcoromano.mooviez.database.Movie

public data class TrendingState(
  val movies: ImmutableList<Movie>,
)

public fun aTrendingState(): TrendingState = TrendingState(
  movies = persistentListOf(
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
)
