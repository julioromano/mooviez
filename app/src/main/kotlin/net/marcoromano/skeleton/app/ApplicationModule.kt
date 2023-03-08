package net.marcoromano.skeleton.app

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
  @Provides
  @Named("httpCacheDir")
  fun httpCacheDir(application: Application): File? = application.cacheDir

  @Provides
  @Named("enableLogging")
  fun enableLogging(): Boolean = BuildConfig.DEBUG

  @Provides
  @Named("tmdbApiKey")
  fun tmdbApiKey(): String = BuildConfig.tmdbApiKey
}
