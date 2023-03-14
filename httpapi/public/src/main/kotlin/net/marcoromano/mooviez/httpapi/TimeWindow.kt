package net.marcoromano.mooviez.httpapi

public enum class TimeWindow(private val timeWindow: String) {
  DAY("day"),
  WEEK("week"),
  ;

  override fun toString(): String = timeWindow
}
