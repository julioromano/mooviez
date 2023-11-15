package net.marcoromano.mooviez.app

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.marcoromano.mooviez.app.ui.AppUi
import net.marcoromano.mooviez.app.ui.theme.AppTheme

fun main() {
  val applicationComponent = ApplicationComponent::class.create()
  application {
    CompositionLocalProvider(
      LocalApplicationComponent provides applicationComponent,
    ) {
      Window(onCloseRequest = ::exitApplication) {
        AppTheme {
          AppUi()
        }
      }
    }
  }
}
