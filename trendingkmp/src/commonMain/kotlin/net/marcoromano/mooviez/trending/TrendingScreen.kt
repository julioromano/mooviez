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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.trending.widgets.trending.TrendingLazyVerticalGrid

public typealias TrendingScreen = @Composable ((id: Long) -> Unit) -> Unit

@Inject
@Composable
public fun TrendingScreen(
  vm: TrendingViewModel,
  navToDetail: (id: Long) -> Unit,
) {
  val scope = rememberCoroutineScope()
  val flow = remember { vm.state(scope) }
  val state by flow.collectAsState()
  TrendingScreen(
    state = state,
    refresh = vm::refresh,
    navToDetail = navToDetail,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrendingScreen(
  state: TrendingState,
  refresh: () -> Unit,
  navToDetail: (id: Long) -> Unit,
) {
  LaunchedEffect(Unit) {
    refresh()
  }
  val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(behavior.nestedScrollConnection),
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
      onMovieClick = { navToDetail(it) },
    )
  }
}
