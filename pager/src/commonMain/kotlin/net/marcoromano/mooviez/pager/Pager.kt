package net.marcoromano.mooviez.pager

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public interface Pager<T> {
  public val pagedData: Flow<PagedData<T>>
}

/**
 * A factory function for creating a [Pager].
 * @param loadPage A function that loads a page of data.
 */
public fun <T> Pager(
  loadPage: suspend (pageNumber: Int) -> Page<T>?,
): Pager<T> = PagerImpl(
  loadPage = loadPage,
)

internal class PagerImpl<T>(
  private val loadPage: suspend (pageNumber: Int) -> Page<T>?,
  dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : Pager<T> {

  // TODO: Don't use a dedicated scope. It should be possible to use the scope of the caller.
  private val scope = CoroutineScope(SupervisorJob() + dispatcher)

  // Should be equally good for random access and append/prepend.
  private val pages = ArrayDeque<Page<T>>()

  private val _pagedData = MutableStateFlow(createPagedDataImpl())

  override val pagedData: Flow<PagedData<T>> = _pagedData.asStateFlow()

  init {
    // This is a bit of a hack, but it's the only way to get the first page to load immediately.
    loadNextPage()
  }

  internal fun loadNextPage() {
    scope.launch {
      val nextPage = loadPage(pages.size + 1)
      if (nextPage != null) {
        pages.add(nextPage)
        _pagedData.value = createPagedDataImpl()
      }
    }
  }

  private fun createPagedDataImpl() = PagedDataImpl(
    pager = this,
    windowSnapshot = pages.map { it.data }.flatten(),
  )
}
