package net.marcoromano.tmdb.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class DatabaseImplTest {
  @Test
  public fun shouldCreateDatabaseInstance() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertNotNull(DatabaseImpl(appContext))
  }
}
