plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.kotlinx.coroutines)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.coroutines.api"
}
