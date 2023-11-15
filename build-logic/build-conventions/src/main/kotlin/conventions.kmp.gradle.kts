/*
 * Kotlin multiplatform library module (convention plugin).
 */

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
  id("org.jetbrains.kotlin.multiplatform")
  id("com.google.devtools.ksp")
  id("com.android.library")
  id("org.jetbrains.compose")
  id("org.jmailen.kotlinter")
}

kotlin {
  explicitApi()
  jvmToolchain(17)

  targets.all {
    compilations.all {
      kotlinOptions {
        allWarningsAsErrors = true
      }
    }
  }

  applyDefaultHierarchyTemplate()
  jvm()
  androidTarget()

  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.findLibrary("kotlinx.coroutines").get())
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.ui)
        implementation(compose.material3)
        implementation(libs.findLibrary("kotlin.inject.runtime").get())
      }
    }
    commonTest {
      dependencies {
        implementation(libs.findLibrary("kotlin.test").get())
        implementation(libs.findLibrary("kotlin.test.junit").get())
        implementation(libs.findLibrary("kotlinx.coroutinesTest").get())
      }
    }
    androidMain {
      dependencies {
        implementation(libs.findLibrary("androidx.lifecycleViewmodelCompose").get())
        implementation(libs.findLibrary("androidx.composeUiTooling").get())
      }
    }
    jvmMain {
      dependencies {
        implementation(compose.preview)
      }
    }
    val androidUnitTest by getting {
      dependencies {
        implementation(libs.findLibrary("androidx.testExtJunit").get())
      }
    }
  }
}

tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask> {
  exclude { it.file.absolutePath.contains("/build/") }
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
    // compose = true // TODO
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.findVersion("androidx.compose.compiler").get().toString()
    useLiveLiterals = true
  }
  testOptions {
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
    animationsDisabled = true
  }
  lint {
    warningsAsErrors = true
  }
}

dependencies {
  // https://kotlinlang.org/docs/ksp-multiplatform.html#compilation-and-processing
  add("kspJvm", libs.findLibrary("kotlin.inject.ksp").get())
}
