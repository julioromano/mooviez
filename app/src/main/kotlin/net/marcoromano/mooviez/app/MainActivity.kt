package net.marcoromano.mooviez.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import dagger.hilt.android.AndroidEntryPoint
import net.marcoromano.mooviez.app.ui.DefaultRootComponentFactory
import net.marcoromano.mooviez.app.ui.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var rootComponentFactory: DefaultRootComponentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val rootComponent = rootComponentFactory.create(defaultComponentContext())
    setContent {
      AppTheme(
        dynamicColor = false,
      ) {
        rootComponent.View()
      }
    }
  }
}
