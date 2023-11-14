import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  id(libs.plugins.kotlin.jvm.get().pluginId)
  id(libs.plugins.compose.mulitplatform.get().pluginId)
  id(libs.plugins.google.ksp.get().pluginId)
  id(libs.plugins.kotlinter.get().pluginId)
}

group = "com.example"
version = "1.0-SNAPSHOT"

dependencies {
  // Note, if you develop a library, you should use compose.desktop.common.
  // compose.desktop.currentOs should be used in launcher-sourceSet
  // (in a separate module for demo project and in testMain).
  // With compose.desktop.common you will also lose @Preview functionality
  implementation(compose.desktop.currentOs)
  implementation(compose.runtime)
  implementation(compose.material3)

  implementation(projects.httpapi.impl)
  implementation(projects.database.impl)
//  implementation(projects.trending) // TODO: Port to kmp
//  implementation(projects.movie) // TODO: Port to kmp
  implementation(projects.inject)
  implementation(libs.kotlin.inject.runtime)
  ksp(libs.kotlin.inject.ksp)
  testImplementation(libs.junit)
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "demo"
      packageVersion = "1.0.0"
    }
  }
}
