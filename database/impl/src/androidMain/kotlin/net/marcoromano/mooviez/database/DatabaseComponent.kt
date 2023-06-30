package net.marcoromano.mooviez.database

import android.content.Context
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope

public interface DatabaseComponent {
  @Provides
  @ApplicationScope
  public fun database(context: Context): Database = AndroidDatabaseFactory(context).create()
}
