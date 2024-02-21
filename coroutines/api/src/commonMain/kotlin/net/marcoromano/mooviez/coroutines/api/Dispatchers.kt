package net.marcoromano.mooviez.coroutines.api

import kotlinx.coroutines.CoroutineDispatcher

/**
 * A wrapper around [kotlinx.coroutines.Dispatchers] to allow for easier testing.
 */
public interface Dispatchers {
  public val Default: CoroutineDispatcher
  public val Main: CoroutineDispatcher
  public val Unconfined: CoroutineDispatcher
  public val IO: CoroutineDispatcher
}
