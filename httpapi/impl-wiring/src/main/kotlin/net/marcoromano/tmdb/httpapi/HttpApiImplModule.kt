package net.marcoromano.mooviez.httpapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class HttpApiImplModule {
  @Provides
  @Singleton
  public fun httpApi(
    @Named("tmdbApiKey") tmdbApiKey: String,
    @Named("httpCacheDir") cacheDir: File?,
    @Named("enableLogging") enableLogging: Boolean,
  ): HttpApi = HttpApiImpl(
    tmdbApiKey = tmdbApiKey,
    cacheDir = cacheDir,
    enableLogging = enableLogging,
  )
}
