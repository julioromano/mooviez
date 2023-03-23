package net.marcoromano.mooviez.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import net.marcoromano.mooviez.app.nodes.RootNode
import net.marcoromano.mooviez.app.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : NodeComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      AppTheme(
        dynamicColor = false,
      ) {
        NodeHost(
          integrationPoint = appyxIntegrationPoint,
        ) {
          RootNode(buildContext = it)
        }
      }
    }
  }
}
