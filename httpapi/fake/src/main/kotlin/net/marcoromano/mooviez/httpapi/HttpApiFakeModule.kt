package net.marcoromano.mooviez.httpapi

// @Module
// @InstallIn(SingletonComponent::class)
public class HttpApiFakeModule {
  //  @Provides
//  @Singleton
  public fun httpApi(): HttpApi = HttpApiFake()
}
