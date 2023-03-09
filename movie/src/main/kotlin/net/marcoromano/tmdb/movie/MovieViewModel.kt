package net.marcoromano.tmdb.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.marcoromano.tmdb.httpapi.HttpApi
import net.marcoromano.tmdb.httpapi.Movie
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
internal class MovieViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val httpApi: HttpApi,
) : ViewModel() {
  private val isLoading = MutableStateFlow(false)
  private val errors = MutableStateFlow<Map<String, Throwable>>(emptyMap())
  private val movie = MutableStateFlow<Movie?>(null)

  val state = combine(isLoading, errors, movie, ::MovieState)
    .stateIn(viewModelScope, SharingStarted.Lazily, MovieState())

  fun load() {
    viewModelScope.launch {
      isLoading.value = true
      runCatching {
        httpApi.movie(movieId = savedStateHandle["id"]!!)
      }.onSuccess {
        movie.value = it
        isLoading.value = false
      }.onFailure { throwable ->
        errors.update {
          it + (UUID.randomUUID().toString() to throwable)
        }
        isLoading.value = false
      }
    }
  }

  fun consumeError(uuid: String) {
    errors.update { it - uuid }
  }
}
