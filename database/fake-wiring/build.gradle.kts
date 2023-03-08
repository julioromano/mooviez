plugins {
  id("conventions.jvm")
  id(libs.plugins.kotlin.kapt.get().pluginId)
}

dependencies {
  api(projects.database.fake)
  implementation(libs.google.daggerHiltCore)
  kapt(libs.google.daggerHiltCompiler)
}
