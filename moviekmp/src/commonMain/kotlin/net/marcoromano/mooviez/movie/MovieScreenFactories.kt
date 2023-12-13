package net.marcoromano.mooviez.movie

import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

public interface MovieScreenComponent {
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
      is MovieScreen -> ui<MovieState> { state, modifier -> Movie(state, modifier) }
      else -> null
    }
  }
}
