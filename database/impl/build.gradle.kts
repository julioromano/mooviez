plugins {
  id("conventions.android")
}

dependencies {
  api(projects.database.public)
  implementation(projects.inject.android)
  implementation(libs.square.sqlDelightAndroid)
  testImplementation(libs.robolectric)
}

android {
  namespace = "net.marcoromano.mooviez.database"
}
