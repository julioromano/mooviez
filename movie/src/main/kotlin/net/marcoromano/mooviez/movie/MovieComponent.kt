package net.marcoromano.mooviez.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import com.arkivanov.decompose.ComponentContext

public interface MovieComponent {
  @Composable
  public fun View()
}

public class DefaultMovieComponent(
  private val componentContext: ComponentContext,
  private val id: Long,
  private val navBack: () -> Unit,
) : MovieComponent, ComponentContext by componentContext {
  @Composable
  override fun View() {
    MovieScreen(
      state = MovieState(
        isLoading = false, errors = mapOf(),
        movie = MovieState.Movie(
          title = AnnotatedString("Movie with id: $id"),
          subtitle = "",
          tagline = "",
          posterPath = "",
          backdropPath = null,
          overview = "",
          userScore = 0,
          releaseDate = "",
        ),
      ),
      navBack = navBack,
    )
  }
}
