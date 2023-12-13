package net.marcoromano.mooviez.app

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Provides
import net.marcoromano.mooviez.inject.ApplicationScope
import net.marcoromano.mooviez.movie.impl.MovieComponent
import net.marcoromano.mooviez.trending.impl.TrendingComponent

interface CircuitComponent : TrendingComponent, MovieComponent {
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
