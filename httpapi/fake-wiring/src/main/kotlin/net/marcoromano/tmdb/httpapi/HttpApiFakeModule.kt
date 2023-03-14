package net.marcoromano.mooviez.httpapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class HttpApiFakeModule {
  @Provides
  @Singleton
  public fun httpApi(): HttpApi = HttpApiFake()
}
