package net.marcoromano.mooviez.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.marcoromano.mooviez.app.ui.AppUi
import net.marcoromano.mooviez.app.ui.theme.AppTheme

fun main() {
  val applicationComponent = ApplicationComponent::class.create()
  application {
    Window(
      onCloseRequest = ::exitApplication,
      title = "Mooviez", // TODO Localize
    ) {
      AppTheme {
        AppUi(
          circuitComponent = applicationComponent,
          exitApplication = ::exitApplication,
        )
      }
    }
  }
}
