package net.marcoromano.mooviez.pager

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PagerTest {
  @Test
  fun `should load a paged list incrementally`() = runTest {
    val loader = SequenceLoader(
      sequence {
        yield((0..9).toList())
        yield((10..19).toList())
        yield((20..29).toList())
        yield((30..39).toList())
      },
    )
    val pager = PagerImpl(
      loadMore = { loader.loadMore() },
    )
    pager.pagedData.test {
      awaitItem().let { item ->
        item.itemCount.shouldBe(0)
      }
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

/**
 * Produces a list of integers and allows loading more of them in chunks of 10.
 */
class ChunkedLoader(private val data: List<Int>) {
  private var index = 0
  suspend fun loadMore(): List<Int>? {
    delay(1)
    if (index >= data.size) return null
    val chunk = data.subList(index, index + 10)
    index += 10
    return chunk
  }
}

class SequenceLoader<T>(data: Sequence<List<T>>) {
  private val iterator = data.iterator()
  suspend fun loadMore(): List<T>? {
    delay(1)
    return if (iterator.hasNext()) iterator.next() else null
  }
}
