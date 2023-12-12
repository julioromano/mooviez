package net.marcoromano.mooviez.app.ui

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import net.marcoromano.mooviez.app.CircuitComponent
import net.marcoromano.mooviez.trending.TrendingScreen

@Composable
fun AppUi(circuitComponent: CircuitComponent) {
  CircuitCompositionLocals(circuitComponent.circuit) {
    val backstack = rememberSaveableBackStack { push(TrendingScreen) }
    val navigator = rememberCircuitNavigator(backstack, onRootPop = {})
    NavigableCircuitContent(navigator, backstack)
  }
}
