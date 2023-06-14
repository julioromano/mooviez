plugins {
  id("conventions.jvm")
}

dependencies {
  api(projects.database.public)
  implementation(projects.inject)
  implementation(libs.square.sqlDelightJvm)
}
