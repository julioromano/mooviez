package net.marcoromano.tmdb.database

import org.junit.Assert.assertNotNull
import org.junit.Test

class DatabaseFakeTest {
  @Test
  fun `should create database instance`() {
    assertNotNull(DatabaseFake())
  }
}
