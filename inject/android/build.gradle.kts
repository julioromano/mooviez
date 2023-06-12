plugins {
  id("conventions.android")
}

android {
  namespace = "net.marcoromano.mooviez.inject"
}

dependencies {
  api(projects.inject.jvm)
}
