package net.marcoromano.mooviez.httpapi

import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.inject.CacheDir
import net.marcoromano.mooviez.inject.IsDebugBuild
import net.marcoromano.mooviez.inject.TmdbApiKey

public interface HttpApiComponent {
  @Provides
  @ApplicationScope
  public fun httpApi(
    tmdbApiKey: TmdbApiKey,
    cacheDir: CacheDir,
    isDebugBuild: IsDebugBuild,
  ): HttpApi = HttpApiImpl(
    tmdbApiKey = tmdbApiKey.value,
    cacheDir = cacheDir.value,
    enableLogging = isDebugBuild.value,
  )
}
