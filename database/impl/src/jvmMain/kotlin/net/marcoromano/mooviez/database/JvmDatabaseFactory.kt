package net.marcoromano.mooviez.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

public class JvmDatabaseFactory : DatabaseFactory {
  override fun create(): Database = Database(
    driver = JdbcSqliteDriver(
      url = JdbcSqliteDriver.IN_MEMORY,
      // url = "jdbc:sqlite:mooviez.db", // TODO: use this for persistent storage pick right dir.
      schema = Database.Schema,
    ),
  )
}
