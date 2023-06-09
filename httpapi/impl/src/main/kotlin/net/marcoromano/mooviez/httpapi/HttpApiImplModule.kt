package net.marcoromano.mooviez.httpapi

import java.io.File

// @Module
// @InstallIn(SingletonComponent::class)
public class HttpApiImplModule {
  //  @Provides
//  @Singleton
  public fun httpApi(
    /*@Named("tmdbApiKey")*/
    tmdbApiKey: String,
    /*@Named("httpCacheDir")*/
    cacheDir: File?,
    /*@Named("enableLogging")*/
    enableLogging: Boolean,
  ): HttpApi = HttpApiImpl(
    tmdbApiKey = tmdbApiKey,
    cacheDir = cacheDir,
    enableLogging = enableLogging,
  )
}
