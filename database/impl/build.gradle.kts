plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.database.public)
        implementation(projects.inject)
      }
    }
    androidMain {
      dependencies {
        implementation(libs.square.sqlDelightAndroid)
      }
    }
    androidUnitTest {
      dependencies {
        implementation(libs.robolectric)
      }
    }
    jvmMain {
      dependencies {
        implementation(libs.square.sqlDelightJvm)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.database"
}
