package net.marcoromano.tmdb.trending

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun TrendingScreen(
  navToDetail: (id: Long) -> Unit,
) {
  val vm = hiltViewModel<TrendingViewModel>()
  val state by vm.state.collectAsStateWithLifecycle()
  TrendingScreen(
    state = state,
    loadTrending = vm::loadTrending,
    navToDetail = navToDetail,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrendingScreen(
  state: TrendingState,
  loadTrending: () -> Unit,
  navToDetail: (id: Long) -> Unit,
) {
  LaunchedEffect(Unit) { loadTrending() }
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.trending)) })
    },
  ) { paddingValues ->
    LazyVerticalGrid(
      columns = GridCells.Adaptive(150.dp),
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize(),
    ) {
      items(state.movies) { movie ->
        Movie(
          movie = movie,
          navToDetail = { navToDetail(it) },
        )
      }
    }
  }
}

@Preview(name = "Day mode")
@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
  TrendingScreen(
    state = TrendingState(),
    loadTrending = {},
    navToDetail = {},
  )
}
