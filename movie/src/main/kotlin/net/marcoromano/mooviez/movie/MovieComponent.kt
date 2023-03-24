package net.marcoromano.mooviez.movie

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import net.marcoromano.mooviez.httpapi.HttpApi

public interface MovieComponent {
  @Composable
  public fun View()
}

public class DefaultMovieComponent(
  private val componentContext: ComponentContext,
  private val httpApi: HttpApi,
  private val id: Long,
  private val navBack: () -> Unit,
) : MovieComponent, ComponentContext by componentContext {

  private val presenter = MoviePresenter(httpApi, id)

  @Composable
  override fun View() {
    val state = presenter.present()
    MovieScreen(
      state = state,
      navBack = navBack,
    )
  }
}
