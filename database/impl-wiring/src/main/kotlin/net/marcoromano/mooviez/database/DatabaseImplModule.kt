package net.marcoromano.mooviez.database

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object DatabaseImplModule {
  @Provides
  @Singleton
  public fun database(application: Application): Database = DatabaseImpl(application)
}
