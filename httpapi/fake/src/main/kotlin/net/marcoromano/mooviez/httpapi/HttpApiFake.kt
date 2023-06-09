package net.marcoromano.mooviez.httpapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okio.buffer
import okio.source
import java.util.Date

internal class HttpApiFake : HttpApi {
  override suspend fun trendingMovies(
    mediaType: MediaType,
    timeWindow: TimeWindow,
    page: Int,
  ): TrendingMovies {
    return when (page) {
      0 -> error("Pages start from number 1")
      1 -> readJson("trending_p1")
      2 -> readJson("trending_p2")
      else -> readJson("trending_p3")
    }
  }

  override suspend fun movie(movieId: Long): Movie {
    return readJson("movie")
  }
}

@OptIn(ExperimentalStdlibApi::class)
private inline fun <reified T> readJson(jsonFileName: String): T = checkNotNull(
  Moshi.Builder()
    .add(Date::class.java, Rfc3339DateJsonAdapter())
    .build()
    .adapter<T>()
    .fromJson(
      HttpApiFake::class.java
        .getResource("/$jsonFileName.json")
        ?.openStream()
        ?.source()
        ?.buffer()!!,
    ),
)
