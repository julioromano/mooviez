plugins {
  id("conventions.jvm")
  id(libs.plugins.square.sqldelight.get().pluginId)
}

sqldelight {
  databases {
    create("Database") {
      packageName.set("net.marcoromano.mooviez.database")
      dialect(libs.square.sqlite18dialect)
      schemaOutputDirectory.set(file("src/main/sqldelight/schema"))
    }
  }
}
