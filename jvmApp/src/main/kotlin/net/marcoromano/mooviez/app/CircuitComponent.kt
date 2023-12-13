package net.marcoromano.mooviez.app

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.movie.MovieScreenComponent
import net.marcoromano.mooviez.trending.TrendingScreenComponent

interface CircuitComponent : TrendingScreenComponent, MovieScreenComponent {
  @Provides
  @ApplicationScope
  fun circuit(
    uiFactories: Set<Ui.Factory>,
    presenterFactories: Set<Presenter.Factory>,
  ): Circuit = Circuit.Builder()
    .addUiFactories(uiFactories)
    .addPresenterFactories(presenterFactories)
    .build()

  val circuit: Circuit

  val uiFactories: Set<Ui.Factory>

  val presenterFactories: Set<Presenter.Factory>
}
