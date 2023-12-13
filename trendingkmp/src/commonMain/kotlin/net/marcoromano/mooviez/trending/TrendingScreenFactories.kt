package net.marcoromano.mooviez.trending

import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

public interface TrendingScreenComponent {
  @Provides
  @IntoSet
  public fun trendingScreenPresenterFactory(
    trendingPresenterFactory: (navigator: Navigator) -> TrendingPresenter,
  ): Presenter.Factory = Presenter.Factory { screen, navigator, _ ->
    when (screen) {
      is TrendingScreen -> trendingPresenterFactory(navigator)
      else -> null
    }
  }

  @Provides
  @IntoSet
  public fun trendingScreenUiFactory(): Ui.Factory = Ui.Factory { screen, _ ->
    when (screen) {
      is TrendingScreen -> ui<TrendingState> { state, modifier -> Trending(state, modifier) }
      else -> null
    }
  }
}