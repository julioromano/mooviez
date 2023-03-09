package net.marcoromano.tmdb.httpapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.Date

public fun HttpApiImpl(
  tmdbApiKey: String,
  baseUrl: String = "https://api.themoviedb.org/",
  cacheDir: File? = null,
  enableLogging: Boolean = false,
): HttpApi = Retrofit.Builder()
  .baseUrl(baseUrl)
  .callFactory(
    OkHttpClient.Builder().apply {
      if (cacheDir != null) cache(Cache(cacheDir, (1024 * 1024 * 100).toLong())) // 100MB
      addInterceptor {
        it.proceed(
          it.request().newBuilder().addHeader("Authorization", "Bearer $tmdbApiKey").build(),
        )
      }
      if (enableLogging) {
        addNetworkInterceptor(
          HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC },
        )
      }
    }.build(),
  )
  .addConverterFactory(
    MoshiConverterFactory.create(
      Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build(),
    ),
  )
  .build()
  .create()
