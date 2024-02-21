plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.coroutines.api)
        implementation(libs.kotlinx.coroutines)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.coroutines.impl"
}