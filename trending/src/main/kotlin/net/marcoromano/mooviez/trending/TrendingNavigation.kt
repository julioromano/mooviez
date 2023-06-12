package net.marcoromano.mooviez.trending

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import me.tatarka.inject.annotations.Inject

@Inject
public class TrendingNavigation(
  private val trendingScreen: TrendingScreen,
) {
  public companion object {
    public const val route: String = "feature"
  }

  @OptIn(ExperimentalAnimationApi::class)
  public fun navGraphBuilder(
    navGraphBuilder: NavGraphBuilder,
    navToDetail: (id: Long) -> Unit,
  ) {
    navGraphBuilder.composable(
      route = route,
    ) {
      trendingScreen(
        navToDetail = navToDetail,
      )
    }
  }

  public fun navigate(navController: NavController) {
    navController.navigate(route)
  }
}
