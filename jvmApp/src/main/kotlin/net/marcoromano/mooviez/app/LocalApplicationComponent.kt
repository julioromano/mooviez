package net.marcoromano.mooviez.app

import androidx.compose.runtime.staticCompositionLocalOf

val LocalApplicationComponent = staticCompositionLocalOf<ApplicationComponent> {
  error("No ApplicationComponent provided")
}
