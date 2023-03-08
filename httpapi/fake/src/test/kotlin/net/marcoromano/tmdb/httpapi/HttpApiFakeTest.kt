package net.marcoromano.tmdb.httpapi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HttpApiFakeTest {
  @Test
  fun `Get a movie`() = runTest {
    assertNotNull(
      HttpApiFake().movie(
        movieId = 1,
      ),
    )
  }

  @Test
  fun `Get trending movies of the week`() = runTest {
    assertNotNull(
      HttpApiFake().trendingMovies(
        page = 1,
      ),
    )
  }
}
