plugins {
  id("conventions.kmp")
  id(libs.plugins.kotlin.parcelize.get().pluginId)
}

android {
  namespace = "net.marcoromano.parcelable"
}
