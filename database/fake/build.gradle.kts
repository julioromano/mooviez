plugins {
  id("conventions.jvm")
}

dependencies {
  api(projects.database.public)
  implementation(projects.inject.jvm)
  implementation(libs.square.sqlDelightJvm)
}
