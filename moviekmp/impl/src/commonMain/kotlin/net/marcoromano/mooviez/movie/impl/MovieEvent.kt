package net.marcoromano.mooviez.movie.impl

import com.slack.circuit.runtime.CircuitUiEvent

public sealed interface MovieEvent : CircuitUiEvent {
  public data object NavBack : MovieEvent
}
