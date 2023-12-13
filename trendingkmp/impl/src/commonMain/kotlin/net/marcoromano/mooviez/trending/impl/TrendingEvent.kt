package net.marcoromano.mooviez.trending.impl

import com.slack.circuit.runtime.CircuitUiEvent

public sealed interface TrendingEvent : CircuitUiEvent {
  public data object Refresh : TrendingEvent
  public data class NavToDetails(val id: Long) : TrendingEvent
}
