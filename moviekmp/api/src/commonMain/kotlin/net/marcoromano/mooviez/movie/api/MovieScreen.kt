package net.marcoromano.mooviez.movie.api

import androidx.compose.ui.text.AnnotatedString
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

public data class MovieScreen(
  val movieId: Long,
) : Screen
