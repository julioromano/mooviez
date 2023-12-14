package net.marcoromano.mooviez.movie.api

import com.slack.circuit.runtime.screen.Screen
import net.marcoromano.parcelable.CommonParcelize

@CommonParcelize
public data class MovieScreen(
  val movieId: Long,
) : Screen
