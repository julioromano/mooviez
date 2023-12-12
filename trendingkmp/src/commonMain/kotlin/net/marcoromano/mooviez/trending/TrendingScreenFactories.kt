package net.marcoromano.mooviez.trending

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

public interface TrendingScreenComponent {
  @Provides
  @IntoSet
  public fun trendingScreenPresenterFactory(f: TrendingScreenPresenterFactory): Presenter.Factory = f

  @Provides
  @IntoSet
  public fun trendingScreenUiFactory(f: TrendingScreenUiFactory): Ui.Factory = f
}

public class TrendingScreenPresenterFactory @Inject constructor(
  private val trendingPresenterFactory: () -> TrendingPresenter,
) : Presenter.Factory {
  override fun create(
    screen: Screen,
    navigator: Navigator,
    context: CircuitContext,
  ): Presenter<*>? {
    return when (screen) {
      is TrendingScreen -> trendingPresenterFactory()
      else -> null
    }
  }
}


public class TrendingScreenUiFactory @Inject constructor() : Ui.Factory {
  override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
    return when (screen) {
      is TrendingScreen -> trendingUi()
      else -> null
    }
  }
}

private fun trendingUi() = ui<TrendingState> { state, modifier ->
  Trending(state, modifier)
}
