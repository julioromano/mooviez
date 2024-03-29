package net.marcoromano.mooviez.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

public fun DatabaseFake(): Database = Database(
  driver = JdbcSqliteDriver(
    url = JdbcSqliteDriver.IN_MEMORY,
  ).apply {
    Database.Schema.create(this)
  },
)
