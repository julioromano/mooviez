package net.marcoromano.mooviez.trending

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import net.marcoromano.mooviez.trending.widgets.trending.TrendingLazyVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Trending(
  state: TrendingState,
  modifier: Modifier = Modifier,
) {
  LaunchedEffect(Unit) {
    state.eventSink(TrendingEvent.Refresh)
  }
  val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  Scaffold(
    modifier = modifier.nestedScroll(behavior.nestedScrollConnection),
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text(text = "Trending") },
        scrollBehavior = behavior,
      )
    },
  ) { paddingValues ->
    TrendingLazyVerticalGrid(
      columns = GridCells.Adaptive(150.dp),
      modifier = Modifier.padding(paddingValues),
      movies = state.movies,
      onMovieClick = { state.eventSink(TrendingEvent.NavToDetails(it)) },
    )
  }
}
