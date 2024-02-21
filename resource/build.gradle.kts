plugins {
  id("conventions.kmp")
}

kotlin {
  sourceSets {
    commonMain {}
  }
}

android {
  namespace = "net.marcoromano.mooviez.resource"
}
