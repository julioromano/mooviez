package net.marcoromano.mooviez.httpapi

import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope

public interface HttpApiComponent {
  @Provides
  @ApplicationScope
  public fun httpApi(): HttpApi = HttpApiFake()
}
