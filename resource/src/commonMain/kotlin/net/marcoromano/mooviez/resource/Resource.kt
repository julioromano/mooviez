package net.marcoromano.mooviez.resource

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Loading-content-error pattern implementation.
 */
public sealed interface Resource<out T> {
  public data object Loading : Resource<Nothing>
  public data class Content<out T>(val value: T) : Resource<T>
  public data class Error<out T>(val error: Throwable) : Resource<T>
}

@OptIn(ExperimentalContracts::class)
public inline fun <T, R> Resource<T>.transform(block: (T) -> R): Resource<R> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }
  return when (this) {
    is Resource.Loading -> Resource.Loading
    is Resource.Error -> Resource.Error(error)
    is Resource.Content -> Resource.Content(block(value))
  }
}
