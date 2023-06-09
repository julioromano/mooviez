package net.marcoromano.mooviez.database

import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope

public interface DatabaseComponent {
  @Provides
  @ApplicationScope
  public fun database(): Database = DatabaseFake()
}
