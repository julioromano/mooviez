package net.marcoromano.tmdb.movie

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable

public object MovieNavigation {
  @OptIn(ExperimentalAnimationApi::class)
  public fun navGraphBuilder(
    navGraphBuilder: NavGraphBuilder,
    navBack: () -> Unit,
  ) {
    navGraphBuilder.composable(
      route = "movie/{id}",
      arguments = listOf(
        navArgument(name = "id") {
          type = NavType.LongType
        },
      ),
    ) {
      MovieScreen(
        navBack = navBack,
      )
    }
  }

  public fun navigate(navController: NavController, id: Long) {
    navController.navigate("movie/$id")
  }
}
