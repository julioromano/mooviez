plugins {
  id("conventions.jvm")
  id(libs.plugins.google.ksp.get().pluginId)
}

dependencies {
  compileOnly(libs.square.retrofit)
  compileOnly(libs.square.moshi)
  ksp(libs.square.moshiKotlinCodegen)
}
