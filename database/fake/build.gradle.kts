plugins {
  id("conventions.jvm")
}

dependencies {
  api(projects.database.public)
  implementation(libs.square.sqlDelightJvm)
}
