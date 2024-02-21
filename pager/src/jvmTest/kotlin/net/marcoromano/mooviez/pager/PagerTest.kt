package net.marcoromano.mooviez.pager

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PagerTest {
  @Test
  fun `should load a paged list incrementally`() = runTest {
    val loader = PageLoader(
      listOf(
        Page(1, (0..9).toList()),
        Page(2, (10..19).toList()),
        Page(3, (20..29).toList()),
        Page(4, (30..39).toList()),
      ),
    )
    val pager = Pager(
      loadPage = { loader.loadMore(it) },
    )
    pager.pagedData.test {
      awaitItem().let { item ->
        item.itemCount.shouldBe(10)
        item[0].shouldBe(0)
        item[9].shouldBe(9)
      }
      awaitItem().let { item ->
        item.itemCount.shouldBe(20)
        item[0].shouldBe(0)
        item[19].shouldBe(19)
      }
      awaitItem().let { item ->
        item.itemCount.shouldBe(30)
        item[0].shouldBe(0)
        item[29].shouldBe(29)
      }
      awaitItem().let { item ->
        item.itemCount.shouldBe(40)
        item[0].shouldBe(0)
        item[39].shouldBe(39)
      }
    }
  }
}

class PageLoader<T>(private val data: List<Page<T>>) {
  suspend fun loadMore(pageNumber: Int): Page<T>? {
    delay(1)
    return data.getOrNull(pageNumber - 1)
  }
}
