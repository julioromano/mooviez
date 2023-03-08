plugins {
  id("conventions.jvm")
  id(libs.plugins.kotlin.kapt.get().pluginId)
}

dependencies {
  api(projects.httpapi.impl)
  implementation(libs.google.daggerHiltCore)
  kapt(libs.google.daggerHiltCompiler)
}
