plugins {
  id("conventions.android")
  id(libs.plugins.kotlin.kapt.get().pluginId)
}

dependencies {
  api(projects.database.impl)
  implementation(libs.google.daggerHiltAndroid)
  kapt(libs.google.daggerHiltCompiler)
}

android {
  namespace = "net.marcoromano.mooviez.database.wiring"
}
