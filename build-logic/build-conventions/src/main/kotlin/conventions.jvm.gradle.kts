/*
 * Kotlin (JVM) library module (convention plugin).
 */

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.google.devtools.ksp")
  id("org.jmailen.kotlinter")
}

kotlin {
  explicitApi()
  jvmToolchain(17)
  compilerOptions {
    allWarningsAsErrors.set(true)
  }
}

tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask> {
  exclude { it.file.absolutePath.contains("/build/") }
}

dependencies {
  implementation(libs.findLibrary("kotlinx.coroutines").get())
  implementation(libs.findLibrary("kotlin.inject.runtime").get())
  ksp(libs.findLibrary("kotlin.inject.ksp").get())
  testImplementation(libs.findLibrary("kotlin.test").get())
  testImplementation(libs.findLibrary("kotlin.test.junit").get())
  testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
  testImplementation(libs.findLibrary("junit").get())
}
