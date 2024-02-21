package net.marcoromano.mooviez.app

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import net.marcoromano.mooviez.app.CircuitComponent
import net.marcoromano.mooviez.trending.api.TrendingScreen

@Composable
fun AppUi(
  circuitComponent: CircuitComponent,
  exitApplication: () -> Unit,
) {
  CircuitCompositionLocals(circuitComponent.circuit) {
    val backstack = rememberSaveableBackStack(root = TrendingScreen)
    val navigator = rememberCircuitNavigator(
      backStack = backstack,
      onRootPop = exitApplication,
    )
    NavigableCircuitContent(
      navigator = navigator,
      backStack = backstack,
    )
  }
}
