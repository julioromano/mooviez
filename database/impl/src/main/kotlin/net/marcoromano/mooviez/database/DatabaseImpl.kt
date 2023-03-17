package net.marcoromano.mooviez.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

public fun DatabaseImpl(context: Context): Database = Database(
  driver = AndroidSqliteDriver(
    schema = Database.Schema,
    context = context,
    name = "net.marcoromano.mooviez.database",
    callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
      override fun onConfigure(db: SupportSQLiteDatabase) {
        super.onConfigure(db)
        db.enableWriteAheadLogging()
        db.setForeignKeyConstraintsEnabled(true)
      }
    },
  ),
)
