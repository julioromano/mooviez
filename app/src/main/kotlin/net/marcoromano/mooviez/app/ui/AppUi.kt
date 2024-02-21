package net.marcoromano.mooviez.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import net.marcoromano.mooviez.app.applicationComponent
import net.marcoromano.mooviez.trending.TrendingNavigation

@Composable
fun AppUi() {
  val navController = rememberNavController()
  val context = LocalContext.current
  NavHost(
    navController = navController,
    startDestination = TrendingNavigation.ROUTE,
    modifier = Modifier.fillMaxSize(),
    route = "mainGraph",
  ) {
    val trendingNavigation = context.applicationComponent.trendingNavigation
    val movieNavigation = context.applicationComponent.movieNavigation
    trendingNavigation.navGraphBuilder(
      navGraphBuilder = this,
      navToDetail = { movieNavigation.navigate(navController, it) },
    )
    movieNavigation.navGraphBuilder(
      navGraphBuilder = this,
      navBack = navController::popBackStack,
    )
  }
}
