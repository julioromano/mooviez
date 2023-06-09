package net.marcoromano.mooviez.database

import android.app.Application

// @Module
// @InstallIn(SingletonComponent::class)
public object DatabaseImplModule {
  //  @Provides
//  @Singleton
  public fun database(application: Application): Database = DatabaseImpl(application)
}
