package net.marcoromano.mooviez.trending

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

public interface TrendingComponent {
  @Composable
  public fun View()
}

public class DefaultTrendingComponent(
  private val componentContext: ComponentContext,
  private val navToDetail: (id: Long) -> Unit,
) : TrendingComponent, ComponentContext by componentContext {
  @Composable
  override fun View() {
    TrendingScreen(
      navToDetail = navToDetail,
    )
  }
}
