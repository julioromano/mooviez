package net.marcoromano.mooviez.pager

public data class Page<T>(
  val pageNumber: Int,
  val data: List<T>,
)
