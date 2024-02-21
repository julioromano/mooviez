plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {}
    jvmTest {
      dependencies {
        implementation(libs.square.turbine)
        implementation(libs.kotest.assertions.core)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.pager"
}
