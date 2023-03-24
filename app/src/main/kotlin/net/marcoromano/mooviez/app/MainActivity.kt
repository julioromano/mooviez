package net.marcoromano.mooviez.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import dagger.hilt.android.AndroidEntryPoint
import net.marcoromano.mooviez.app.ui.theme.AppTheme
import net.marcoromano.mooviez.app.ui.DefaultRootComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val rootComponent = DefaultRootComponent(defaultComponentContext())
    setContent {
      AppTheme(
        dynamicColor = false,
      ) {
        rootComponent.View()
      }
    }
  }
}
