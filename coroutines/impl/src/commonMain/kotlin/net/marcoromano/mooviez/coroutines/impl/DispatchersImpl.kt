package net.marcoromano.mooviez.coroutines.impl

import kotlinx.coroutines.CoroutineDispatcher
import net.marcoromano.mooviez.coroutines.api.Dispatchers

public object DispatchersImpl : Dispatchers {
  override val Default: CoroutineDispatcher
    get() = kotlinx.coroutines.Dispatchers.Default
  override val Main: CoroutineDispatcher
    get() = kotlinx.coroutines.Dispatchers.Main
  override val Unconfined: CoroutineDispatcher
    get() = kotlinx.coroutines.Dispatchers.Unconfined
  override val IO: CoroutineDispatcher
    get() = kotlinx.coroutines.Dispatchers.IO
}
