package net.marcoromano.mooviez.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.marcoromano.mooviez.app.ui.AppUi
import net.marcoromano.mooviez.app.ui.theme.AppTheme

fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    AppTheme {
      AppUi()
    }
  }
}
