package net.marcoromano.tmdb.trending

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.marcoromano.tmdb.database.DatabaseFake
import net.marcoromano.tmdb.httpapi.HttpApiFake
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class TrendingViewModelTest {

  @get:Rule
  val coroutineScope = MainCoroutineScopeRule()

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `greets the person with their name`() = runTest {
    val vm = TrendingViewModel(
      httpApi = HttpApiFake(),
      database = DatabaseFake(),
    )
    vm.state.test {
      assertEquals(TrendingState(), awaitItem())
      vm.loadTrending()
      cancelAndIgnoreRemainingEvents()
      // assertEquals(TrendingState(), awaitItem()) // TODO
    }
  }
}
