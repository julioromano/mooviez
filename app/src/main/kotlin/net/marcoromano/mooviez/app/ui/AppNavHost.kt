package net.marcoromano.mooviez.app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import net.marcoromano.mooviez.app.applicationComponent
import net.marcoromano.mooviez.trending.TrendingNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost() {
  val navController = rememberAnimatedNavController()
  val context = LocalContext.current
  AnimatedNavHost(
    navController = navController,
    startDestination = TrendingNavigation.route,
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
