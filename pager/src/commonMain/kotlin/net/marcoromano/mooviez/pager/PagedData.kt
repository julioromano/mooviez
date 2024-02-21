package net.marcoromano.mooviez.pager

public interface PagedData<T> {
  /**
   * The number of items loaded so far.
   *
   * This value is guaranteed to be at least the number of items in the last [PagedData] snapshot.
   * It may be greater if more items have been loaded since the last snapshot.
   */
  public val itemCount: Int
  public operator fun get(index: Int): T?
}

internal class PagedDataImpl<T>(
  private val pager: PagerImpl<T>,
  private val windowSnapshot: List<T>,
) : PagedData<T> {
  companion object {
    private const val THRESHOLD = 5
  }

  private var loadingMore: Boolean = false

  override val itemCount: Int
    get() = windowSnapshot.size

  override operator fun get(index: Int): T? {
    if (itemCount - index < THRESHOLD && !loadingMore) {
      loadingMore = true
      pager.loadMore()
    }
    return windowSnapshot.getOrNull(index)
  }
}
