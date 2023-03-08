package net.marcoromano.tmdb.app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import net.marcoromano.tmdb.trending.TrendingNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost() {
  val navController = rememberAnimatedNavController()
  AnimatedNavHost(
    navController = navController,
    startDestination = TrendingNavigation.route,
    modifier = Modifier.fillMaxSize(),
    route = "mainGraph",
  ) {
    TrendingNavigation.navGraphBuilder(
      navGraphBuilder = this,
      navToDetail = { /* TODO() */ },
    )
  }
}
