plugins {
  id("conventions.android")
}

dependencies {
  api(projects.database.public)
  implementation(libs.square.sqlDelightAndroid)
  testImplementation(libs.robolectric)
}

android {
  namespace = "net.marcoromano.tmdb.database"
}
