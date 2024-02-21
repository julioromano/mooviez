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

internal class PagerImpl<T>(
  private val loadMore: suspend () -> List<T>?,
  dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : Pager<T> {

  private val scope = CoroutineScope(SupervisorJob() + dispatcher)

  // Should be equally good for random access and append/prepend.
  private val window = ArrayDeque<T>()

  private val _pagedData = MutableStateFlow(PagedDataImpl(this, window.toList()))

  override val pagedData: Flow<PagedData<T>> = _pagedData.asStateFlow()

  init {
    // This is a bit of a hack, but it's the only way to get the first page to load immediately.
    loadMore()
  }

  internal fun loadMore() {
    scope.launch {
      val moreData = loadMore.invoke()
      if (moreData != null) {
        window.addAll(moreData)
        _pagedData.value = PagedDataImpl(this@PagerImpl, window.toList())
      }
    }
  }
}
