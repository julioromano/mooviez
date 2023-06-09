package net.marcoromano.mooviez.database

// @Module
// @InstallIn(SingletonComponent::class)
public object DatabaseFakeModule {
  //  @Provides
//  @Singleton
  public fun database(): Database = DatabaseFake()
}
