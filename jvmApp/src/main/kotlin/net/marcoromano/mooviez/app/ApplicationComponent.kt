package net.marcoromano.mooviez.app

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.database.DatabaseComponent
import net.marcoromano.mooviez.httpapi.HttpApiComponent
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.inject.CacheDir
import net.marcoromano.mooviez.inject.IsDebugBuild
import net.marcoromano.mooviez.inject.TmdbApiKey
import net.marcoromano.mooviez.trending.TrendingScreen
import net.marcoromano.mooviez.trending.TrendingViewModel
import java.io.File

@ApplicationScope
@Component
abstract class ApplicationComponent : DatabaseComponent, HttpApiComponent {

  abstract val trendingViewModel: TrendingViewModel
  abstract val trendingScreen: TrendingScreen

  @Provides
  fun tmdbApiKey(): TmdbApiKey = TmdbApiKey("asdasd")

  @Provides
  fun cacheDir(): CacheDir = CacheDir(File(""))

  @Provides
  fun isDebugBuild(): IsDebugBuild = IsDebugBuild(false)
}
