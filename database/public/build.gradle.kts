plugins {
  id("conventions.jvm")
  id(libs.plugins.square.sqldelight.get().pluginId)
}

sqldelight {
  database("Database") {
    packageName = "net.marcoromano.tmdb.database"
    schemaOutputDirectory = file("src/main/sqldelight/schema")
  }
}
