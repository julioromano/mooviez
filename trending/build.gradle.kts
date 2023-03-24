plugins {
  id("conventions.android")
}

android {
  namespace = "net.marcoromano.mooviez.trending"
}

dependencies {
  implementation(projects.widgets)
  implementation(projects.database.public)
  implementation(projects.httpapi.public)
  implementation(libs.coilCompose)
  implementation(libs.androidx.pagingRuntime)
  implementation(libs.androidx.pagingCompose)
  implementation(libs.decompose)
  implementation(libs.decompose.extensionsComposeJetpack)
  implementation(libs.google.accompanistPlaceholderMaterial)
  implementation(libs.square.sqlDelightAndroidPaging3)
  implementation(libs.square.sqlDelightCoroutines)
  testImplementation(libs.square.turbine)
  testImplementation(projects.database.fake)
  testImplementation(projects.httpapi.fake)
}
