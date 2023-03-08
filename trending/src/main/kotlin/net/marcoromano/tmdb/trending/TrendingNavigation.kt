package net.marcoromano.tmdb.trending

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

public object TrendingNavigation {
  public const val route: String = "feature"

  @OptIn(ExperimentalAnimationApi::class)
  public fun navGraphBuilder(
    navGraphBuilder: NavGraphBuilder,
    navToDetail: (id: Long) -> Unit,
  ) {
    navGraphBuilder.composable(
      route = route,
    ) {
      TrendingScreen(
        navToDetail = navToDetail,
      )
    }
  }

  public fun navigate(navController: NavController) {
    navController.navigate(route)
  }
}
