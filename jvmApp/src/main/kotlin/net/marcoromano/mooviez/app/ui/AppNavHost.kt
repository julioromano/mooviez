package net.marcoromano.mooviez.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppNavHost() {
  // TODO: create custom navhost that ties together all UI features into a navigable UI.
  Scaffold {
    Column {
      Text("CIAO")
      Button(onClick = { /*TODO*/ }) {
        Text("Click me")
      }
    }
  }
}
