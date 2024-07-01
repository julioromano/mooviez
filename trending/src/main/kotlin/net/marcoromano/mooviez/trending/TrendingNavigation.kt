package net.marcoromano.mooviez.trending

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.tatarka.inject.annotations.Inject

@Inject
public class TrendingNavigation(private val trendingScreen: TrendingScreen) {
  public companion object {
    public const val ROUTE: String = "feature"
  }

  public fun navGraphBuilder(navGraphBuilder: NavGraphBuilder, navToDetail: (id: Long) -> Unit) {
    navGraphBuilder.composable(
      route = ROUTE,
    ) {
      trendingScreen.Composable(
        navToDetail,
      )
    }
  }

  public fun navigate(navController: NavController) {
    navController.navigate(ROUTE)
  }
}
