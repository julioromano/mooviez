package net.marcoromano.mooviez.app

import android.app.Application
import java.io.File

// @Module
// @InstallIn(SingletonComponent::class)
object ApplicationModule {
  //  @Provides
//  @Named("httpCacheDir")
  fun httpCacheDir(application: Application): File? = application.cacheDir

  //  @Provides
//  @Named("enableLogging")
  fun enableLogging(): Boolean = BuildConfig.DEBUG

  //  @Provides
//  @Named("tmdbApiKey")
  fun tmdbApiKey(): String = BuildConfig.TMDB_API_KEY
}
