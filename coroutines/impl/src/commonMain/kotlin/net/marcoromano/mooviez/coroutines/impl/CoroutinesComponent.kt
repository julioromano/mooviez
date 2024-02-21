package net.marcoromano.mooviez.coroutines.impl

import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.coroutines.api.Dispatchers

public interface CoroutinesComponent {
  @Provides
  public fun dispatchers(): Dispatchers = DispatchersImpl
}

