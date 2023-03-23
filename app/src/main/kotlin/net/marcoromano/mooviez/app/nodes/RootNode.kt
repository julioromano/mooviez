package net.marcoromano.mooviez.app.nodes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

internal class RootNode(
  buildContext: BuildContext,
) : Node(buildContext = buildContext) {
  @JvmField
  val lifecycle: Lifecycle = nodeLifecycle.lifecycle

  @Composable
  override fun View(modifier: Modifier) {
    Text("Hello world!")
  }
}
