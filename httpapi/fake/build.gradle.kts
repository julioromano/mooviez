plugins {
  id("conventions.jvm")
}

dependencies {
  api(projects.httpapi.public)
  implementation(projects.inject.jvm)
  implementation(libs.square.okio)
  implementation(libs.square.moshi)
  implementation(libs.square.moshiAdapters)
}
