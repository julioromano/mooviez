package net.marcoromano.mooviez.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.coroutines.impl.CoroutinesComponent
import net.marcoromano.mooviez.database.DatabaseComponent
import net.marcoromano.mooviez.httpapi.HttpApiComponent
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.inject.CacheDir
import net.marcoromano.mooviez.inject.IsDebugBuild
import net.marcoromano.mooviez.inject.TmdbApiKey
import java.io.File

@ApplicationScope
@Component
abstract class ApplicationComponent :
  CircuitComponent,
  DatabaseComponent,
  HttpApiComponent,
  CoroutinesComponent {
  @Provides
  fun tmdbApiKey(): TmdbApiKey =
    TmdbApiKey("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NGZmYjU2ZDNjZDY1YjgxMmYxYzhjMjQwMTUyYmYzNCIsInN1YiI6IjY0MGFlNWMyMThiNzUxMDA3OWFkMDllOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TdcuDVXYl2O5EhOOsYblg3He-Qn2nmq0y53QdB62utw")

  @Provides
  fun cacheDir(): CacheDir = CacheDir(File(""))

  @Provides
  fun isDebugBuild(): IsDebugBuild = IsDebugBuild(false)

  @Provides
  @ApplicationScope
  fun scope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}
