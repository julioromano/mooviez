package net.marcoromano.mooviez.app.ui

import androidx.compose.runtime.Composable
import net.marcoromano.mooviez.app.LocalApplicationComponent

@Composable
fun AppNavHost() {
  // TODO: create custom navhost that ties together all UI features into a navigable UI.
  val trendingScreen = LocalApplicationComponent.current.trendingScreen
  trendingScreen({ _ -> })
}
