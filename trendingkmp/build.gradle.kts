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
        implementation(projects.moviekmp) // TODO: Import only the screen entry point
        implementation(libs.kamel)
        implementation(libs.kotlinx.collections.immutable)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.square.sqlDelightCoroutines)
        implementation(libs.circuit.foundation)
      }
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.trending"
}
