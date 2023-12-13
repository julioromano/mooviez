plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.circuit.runtime)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.movie.api"
}
