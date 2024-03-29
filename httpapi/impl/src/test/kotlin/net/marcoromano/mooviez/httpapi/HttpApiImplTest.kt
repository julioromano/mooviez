package net.marcoromano.mooviez.httpapi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HttpApiImplTest {
  @Test
  fun `it injects the api key`() = runTest {
    val server = MockWebServer().apply {
      enqueue(
        MockResponse().setBody(
          body = fakeMovie,
        ),
      )
      start()
    }
    val url = server.url("")

    HttpApiImpl(
      tmdbApiKey = "aKey",
      baseUrl = url.toString(),
      cacheDir = null,
      enableLogging = false,
    ).movie(
      movieId = 1,
    )

    val req = server.takeRequest()
    assertEquals("Bearer aKey", req.getHeader("Authorization"))
  }

  @Test
  fun `Get a movie`() = runTest {
    val server = MockWebServer().apply {
      enqueue(
        MockResponse().setBody(
          body = fakeMovie,
        ),
      )
      start()
    }
    val url = server.url("")

    HttpApiImpl(
      tmdbApiKey = "aKey",
      baseUrl = url.toString(),
      cacheDir = null,
      enableLogging = false,
    ).movie(
      movieId = 1,
    )

    val req = server.takeRequest()
    assertEquals("GET", req.method)
    assertEquals("/3/movie/1", req.path)
  }

  @Test
  fun `Get trending movies of the week`() = runTest {
    val server = MockWebServer().apply {
      enqueue(
        MockResponse().setBody(
          """
          {
            "page": 3,
            "results": [],
            "total_pages": 1000,
            "total_results": 20000
          }
          """.trimIndent(),
        ),
      )
      start()
    }
    val url = server.url("")

    HttpApiImpl(
      tmdbApiKey = "aKey",
      baseUrl = url.toString(),
      cacheDir = null,
      enableLogging = false,
    ).trendingMovies(
      page = 1,
    )

    val req = server.takeRequest()
    assertEquals("GET", req.method)
    assertEquals("/3/trending/movie/week?page=1", req.path)
  }
}

private val fakeMovie = """
  {
      "adult": false,
      "backdrop_path": "/jG8qyfFdfbxmvmYEYFUx64toKIp.jpg",
      "belongs_to_collection": null,
      "budget": 0,
      "genres": [
          {
              "id": 35,
              "name": "Comedy"
          }
      ],
      "homepage": "",
      "id": 261414,
      "imdb_id": "tt0218388",
      "original_language": "en",
      "original_title": "Larry David: Curb Your Enthusiasm",
      "overview": "Mock documentary about Seinfeld writer Larry David featuring contributions from his friends and colleagues. Larry makes a return to stand-up comedy and prepares to film a television special for HBO.  This is the original special that gave birth to the long-running award-winning HBO series.",
      "popularity": 4.342,
      "poster_path": "/j65RZ1BCnNRrjoWp66lqTwSQ7UX.jpg",
      "production_companies": [],
      "production_countries": [
          {
              "iso_3166_1": "US",
              "name": "United States of America"
          }
      ],
      "release_date": "1999-10-17",
      "revenue": 0,
      "runtime": 59,
      "spoken_languages": [
          {
              "english_name": "English",
              "iso_639_1": "en",
              "name": "English"
          },
          {
              "english_name": "Spanish",
              "iso_639_1": "es",
              "name": "Español"
          }
      ],
      "status": "Released",
      "tagline": "",
      "title": "Larry David: Curb Your Enthusiasm",
      "video": false,
      "vote_average": 7.9,
      "vote_count": 46
  }
""".trimIndent()
