package net.marcoromano.mooviez.app

import android.app.Application
import android.content.Context
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.database.DatabaseComponent
import net.marcoromano.mooviez.httpapi.HttpApiComponent
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.inject.CacheDir
import net.marcoromano.mooviez.inject.IsDebugBuild
import net.marcoromano.mooviez.inject.TmdbApiKey
import net.marcoromano.mooviez.movie.MovieNavigation
import net.marcoromano.mooviez.trending.TrendingNavigation

@ApplicationScope
@Component
abstract class ApplicationComponent(private val application: Application) :
  DatabaseComponent,
  HttpApiComponent {
  abstract val trendingNavigation: TrendingNavigation
  abstract val movieNavigation: MovieNavigation

  @Provides
  fun context(): Context = application

  @Provides
  fun tmdbApiKey(): TmdbApiKey = TmdbApiKey(BuildConfig.TMDB_API_KEY)

  @Provides
  fun cacheDir(): CacheDir = CacheDir(application.cacheDir)

  @Provides
  fun isDebugBuild(): IsDebugBuild = IsDebugBuild(BuildConfig.DEBUG)
}
