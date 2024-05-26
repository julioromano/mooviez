package net.marcoromano.mooviez.movie

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.tatarka.inject.annotations.Inject

@Inject
public class MovieNavigation(
  private val movieScreen: MovieScreen,
) {
  public fun navGraphBuilder(navGraphBuilder: NavGraphBuilder, navBack: () -> Unit) {
    navGraphBuilder.composable(
      route = "movie/{id}",
      arguments = listOf(
        navArgument(name = "id") {
          type = NavType.LongType
        },
      ),
    ) {
      movieScreen.Composable(
        navBack,
      )
    }
  }

  public fun navigate(navController: NavController, id: Long) {
    navController.navigate("movie/$id")
  }
}
