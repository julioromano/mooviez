plugins {
  id("conventions.kmp")
  id(libs.plugins.square.sqldelight.get().pluginId)
}

sqldelight {
  databases {
    create("Database") {
      packageName.set("net.marcoromano.mooviez.database")
      dialect(libs.square.sqlite18dialect)
      schemaOutputDirectory.set(file("src/commonMain/sqldelight/schema"))
    }
  }
}

android {
  namespace = "net.marcoromano.mooviez.database.api"
}
