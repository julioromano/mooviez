package net.marcoromano.tmdb.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.marcoromano.tmdb.database.Database
import net.marcoromano.tmdb.httpapi.HttpApi
import net.marcoromano.tmdb.httpapi.TrendingMovies
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
internal class TrendingViewModel @Inject constructor(
  private val httpApi: HttpApi,
  private val database: Database, // TODO("Offline usage")
) : ViewModel() {

  private val isLoading = MutableStateFlow(false)
  private val errors = MutableStateFlow<Map<String, Throwable>>(emptyMap())
  private val movies = MutableStateFlow<List<TrendingMovies.Movie>>(emptyList())

  val state = combine(isLoading, errors, movies, ::TrendingState)
    .stateIn(viewModelScope, SharingStarted.Lazily, TrendingState())

  fun loadTrending(page: Int = 1) {
    viewModelScope.launch {
      runCatching {
        httpApi.trendingMovies(page = page)
      }.onSuccess {
        movies.value = it.results
      }.onFailure { throwable ->
        errors.update {
          it + (UUID.randomUUID().toString() to throwable)
        }
      }
    }
  }

  fun consumeError(uuid: String) {
    errors.update { it - uuid }
  }
}
