plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.circuit.runtime)
        implementation(projects.parcelable)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.trending.api"
}
