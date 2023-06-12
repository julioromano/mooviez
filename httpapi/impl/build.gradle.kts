plugins {
  id("conventions.jvm")
}

dependencies {
  api(projects.httpapi.public)
  implementation(projects.inject.jvm)
  implementation(libs.square.okio)
  implementation(libs.square.okhttp)
  implementation(libs.square.okhttpLoggingInterceptor)
  implementation(libs.square.retrofit)
  implementation(libs.square.retrofitConverterMoshi)
  implementation(libs.square.moshi)
  implementation(libs.square.moshiAdapters)
  testImplementation(libs.square.okhttpMockwebserver)
}
