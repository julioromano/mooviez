package net.marcoromano.mooviez.app

import android.content.Context

class Application : android.app.Application() {
  val applicationComponent: ApplicationComponent by lazy {
    ApplicationComponent::class.create(this)
  }
}

val Context.applicationComponent: ApplicationComponent
  get() = (applicationContext as Application).applicationComponent
