package net.marcoromano.mooviez.trending.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toImmutableList
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.movie.api.MovieScreen

@Inject
public class TrendingPresenter(
  private val moviesRepo: MoviesRepo,
  @Assisted private val navigator: Navigator,
) : Presenter<TrendingState> {
  @Composable
  override fun present(): TrendingState {

    val movies by moviesRepo.movies.collectAsState()
    val isLoadingMore by moviesRepo.isLoadingMore.collectAsState()

    return TrendingState(
      movies = movies.toImmutableList(),
      isLoadingMore = isLoadingMore,
    ) {
      when (it) {
        is TrendingEvent.Refresh -> moviesRepo.loadMore()
        is TrendingEvent.NavToDetails -> navigator.goTo(MovieScreen(it.id))
      }
    }
  }
}
