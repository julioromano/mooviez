plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.trendingkmp.api)
        implementation(projects.inject)
        implementation(projects.widgets)
        implementation(projects.database.public)
        implementation(projects.httpapi.public)
        implementation(projects.moviekmp.api)
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
  namespace = "net.marcoromano.mooviez.trending.impl"
}