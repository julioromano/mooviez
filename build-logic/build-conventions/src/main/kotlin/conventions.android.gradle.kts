/*
 * Kotlin (Android) library module (convention plugin).
 */

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.plugin.compose")
  id("com.google.devtools.ksp")
  id("org.jmailen.kotlinter")
}

android {
  buildToolsVersion = libs.findVersion("android.buildTools").get().toString()
  compileSdk = libs.findVersion("android.compileSdk").get().toString().toInt()
  defaultConfig {
    minSdk = libs.findVersion("android.minSdk").get().toString().toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments["clearPackageData"] = "true"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    useLiveLiterals = true
  }
  testOptions {
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
    animationsDisabled = true
  }
}

kotlin {
  explicitApi()
  jvmToolchain(17)
  compilerOptions {
    allWarningsAsErrors.set(true)
    freeCompilerArgs.addAll(
      "-P",
      "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_metrics",
      "-P",
      "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_reports",
    )
  }
}

dependencies {
  implementation(libs.findBundle("androidx.compose").get())
  implementation(libs.findLibrary("androidx.activityCompose").get())
  implementation(libs.findLibrary("androidx.core").get())
  implementation(libs.findLibrary("androidx.lifecycleRuntimeCompose").get())
  implementation(libs.findLibrary("androidx.lifecycleViewmodelCompose").get())
  implementation(libs.findLibrary("androidx.navigation.compose").get())
  implementation(libs.findLibrary("kotlinx.coroutinesAndroid").get())
  implementation(platform(libs.findLibrary("androidx.compose.bom").get()))
  debugImplementation(libs.findLibrary("androidx-composeUiTestManifest").get())
  debugImplementation(libs.findLibrary("androidx.composeUiTooling").get())
  implementation(libs.findLibrary("kotlin.inject.runtime").get())
  ksp(libs.findLibrary("kotlin.inject.ksp").get())
  testImplementation(libs.findLibrary("androidx.testExtJunit").get())
  testImplementation(libs.findLibrary("junit").get())
  testImplementation(libs.findLibrary("kotlin.test").get())
  testImplementation(libs.findLibrary("kotlin.test.junit").get())
  testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
  androidTestImplementation(libs.findLibrary("androidx.composeUiTestJunit4").get())
  androidTestImplementation(libs.findLibrary("androidx.testExtJunit").get())
  androidTestImplementation(libs.findLibrary("androidx.testRules").get())
  androidTestImplementation(libs.findLibrary("androidx.testRunner").get())
  androidTestImplementation(libs.findLibrary("junit").get())
  androidTestImplementation(libs.findLibrary("kotlin.test").get())
  androidTestImplementation(libs.findLibrary("kotlin.test.junit").get())
  androidTestImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
  androidTestUtil(libs.findLibrary("androidx.testOrchestrator").get())
}
