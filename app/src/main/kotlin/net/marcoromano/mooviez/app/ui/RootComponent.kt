package net.marcoromano.mooviez.app.ui

import android.os.Parcelable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize
import net.marcoromano.mooviez.httpapi.HttpApi
import net.marcoromano.mooviez.movie.DefaultMovieComponent
import net.marcoromano.mooviez.movie.MovieComponent
import net.marcoromano.mooviez.trending.DefaultTrendingComponent
import net.marcoromano.mooviez.trending.TrendingComponent

interface RootComponent {
  val stack: Value<ChildStack<*, Child>>

  sealed class Child {
    class TrendingChild(val component: TrendingComponent) : Child()
    class MovieChild(val component: MovieComponent) : Child()
  }
}

@AssistedFactory
interface DefaultRootComponentFactory {
  fun create(componentContext: ComponentContext): DefaultRootComponent
}

class DefaultRootComponent @AssistedInject constructor(
  private val httpApi: HttpApi,
  @Assisted componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

  private val navigation = StackNavigation<Config>()

  private val _stack = childStack(
    source = navigation,
    initialConfiguration = Config.Trending, // The initial child component is List
    handleBackButton = true, // Automatically pop from the stack on back button presses
    childFactory = ::child,
  )

  override val stack: Value<ChildStack<*, RootComponent.Child>> = _stack

  private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
    when (config) {
      is Config.Trending -> RootComponent.Child.TrendingChild(
        DefaultTrendingComponent(
          componentContext = componentContext,
          navToDetail = { navigation.push(Config.Movie(id = it)) },
        ),
      )
      is Config.Movie -> RootComponent.Child.MovieChild(
        DefaultMovieComponent(
          componentContext = componentContext,
          httpApi = httpApi,
          id = config.id,
          navBack = navigation::pop,
        ),
      )
    }

  @Parcelize
  private sealed interface Config : Parcelable {
    object Trending : Config
    data class Movie(val id: Long) : Config
  }

  @Composable
  fun View() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    DisposableEffect(systemUiController, useDarkIcons) {
      systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = useDarkIcons,
      )
      onDispose {}
    }
    AppUi(
      rootComponent = this,
    )
  }
}
