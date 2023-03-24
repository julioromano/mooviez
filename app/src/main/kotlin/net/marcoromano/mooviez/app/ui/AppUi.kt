package net.marcoromano.mooviez.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppUi(
  rootComponent: RootComponent,
) {
  val systemUiController = rememberSystemUiController()
  val useDarkIcons = !isSystemInDarkTheme()
  DisposableEffect(systemUiController, useDarkIcons) {
    systemUiController.setSystemBarsColor(
      color = Color.Transparent,
      darkIcons = useDarkIcons,
    )
    onDispose {}
  }
  Children(
    stack = rootComponent.stack,
    modifier = Modifier.fillMaxSize(),
    animation = stackAnimation(fade() + scale()),
  ) {
    when (val child = it.instance) {
      is RootComponent.Child.TrendingChild -> child.component.View()
      is RootComponent.Child.MovieChild -> child.component.View()
    }
  }
}
