package net.marcoromano.mooviez.httpapi

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface HttpApi {
  @GET("3/trending/{mediaType}/{timeWindow}")
  public suspend fun trendingMovies(
    @Path("mediaType") mediaType: MediaType = MediaType.MOVIE,
    @Path("timeWindow") timeWindow: TimeWindow = TimeWindow.WEEK,
    @Query("page") page: Int = 1,
  ): TrendingMovies

  @GET("3/movie/{movie_id}")
  public suspend fun movie(@Path("movie_id") movieId: Long): Movie
}
