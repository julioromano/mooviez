package net.marcoromano.mooviez.trending

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
public class MainCoroutineScopeRule(
  public val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

  public override fun starting(description: Description) {
    super.starting(description)
    Dispatchers.setMain(dispatcher)
  }

  public override fun finished(description: Description) {
    super.finished(description)
    Dispatchers.resetMain()
  }
}
