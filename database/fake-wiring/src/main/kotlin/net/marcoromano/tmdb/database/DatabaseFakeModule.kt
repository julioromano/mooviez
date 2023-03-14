package net.marcoromano.mooviez.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object DatabaseFakeModule {
  @Provides
  @Singleton
  public fun database(): Database = DatabaseFake()
}
