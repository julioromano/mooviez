package net.marcoromano.mooviez.coroutines.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import net.marcoromano.mooviez.coroutines.api.Dispatchers

public fun TestScope.testDispatchers(
  unconfined: Boolean = false,
): Dispatchers = DispatchersTestImpl(
  unconfined = unconfined,
  scheduler = testScheduler,
)

private class DispatchersTestImpl(
  unconfined: Boolean,
  scheduler: TestCoroutineScheduler,
) : Dispatchers {
  @OptIn(ExperimentalCoroutinesApi::class)
  private val dispatcher: CoroutineDispatcher =
    if (unconfined) UnconfinedTestDispatcher(scheduler) else StandardTestDispatcher(scheduler)

  override val Default: CoroutineDispatcher
    get() = dispatcher
  override val Main: CoroutineDispatcher
    get() = dispatcher
  override val Unconfined: CoroutineDispatcher
    get() = dispatcher
  override val IO: CoroutineDispatcher
    get() = dispatcher
}
