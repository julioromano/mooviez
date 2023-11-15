plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(projects.inject)
        implementation(projects.widgets)
        implementation(projects.database.public)
        implementation(projects.httpapi.public)
        implementation(libs.kamel)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.square.sqlDelightCoroutines)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.trending"
}
