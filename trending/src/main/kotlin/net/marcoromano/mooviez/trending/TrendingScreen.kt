package net.marcoromano.mooviez.trending

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.marcoromano.mooviez.trending.widgets.trending.TrendingLazyVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrendingScreen(
  navToDetail: (id: Long) -> Unit,
) {
  val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(behavior.nestedScrollConnection),
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.trending)) },
        scrollBehavior = behavior,
      )
    },
  ) { paddingValues ->
    TrendingLazyVerticalGrid(
      columns = GridCells.Adaptive(150.dp),
      modifier = Modifier.padding(paddingValues),
      onMovieClick = { navToDetail(it) },
    )
  }
}

@Preview(name = "Day mode")
@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
  TrendingScreen(
    navToDetail = {},
  )
}
