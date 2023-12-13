package net.marcoromano.mooviez.movie.impl

import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.movie.api.MovieScreen

public interface MovieComponent {
  @Provides
  @IntoSet
  public fun movieScreenPresenterFactory(
    moviePresenterFactory: (movieId: Long, navigator: Navigator) -> MoviePresenter,
  ): Presenter.Factory = Presenter.Factory { screen, navigator, _ ->
    when (screen) {
      is MovieScreen -> moviePresenterFactory(screen.movieId, navigator)
      else -> null
    }
  }

  @Provides
  @IntoSet
  public fun movieScreenUiFactory(): Ui.Factory = Ui.Factory { screen, _ ->
    when (screen) {
      is MovieScreen -> ui<MovieState> { state, modifier -> MovieView(state, modifier) }
      else -> null
    }
  }
}
