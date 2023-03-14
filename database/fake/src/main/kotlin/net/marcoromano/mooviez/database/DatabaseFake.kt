package net.marcoromano.mooviez.database

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

public fun DatabaseFake(): Database = Database(
  driver = JdbcSqliteDriver(
    url = JdbcSqliteDriver.IN_MEMORY,
  ).apply {
    Database.Schema.create(this)
  },
)
