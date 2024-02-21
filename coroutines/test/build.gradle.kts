plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.coroutines.api)
        implementation(libs.kotlinx.coroutines)
        implementation(libs.kotlinx.coroutinesTest)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.coroutines.test"
}
