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
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.tatarka.inject.annotations.Inject
import net.marcoromano.mooviez.database.Movie
import net.marcoromano.mooviez.inject.viewModel
import net.marcoromano.mooviez.trending.widgets.trending.TrendingLazyVerticalGrid

public typealias TrendingScreen = @Composable ((id: Long) -> Unit) -> Unit

@Inject
@Composable
public fun TrendingScreen(
  trendingViewModel: () -> TrendingViewModel,
  navToDetail: (id: Long) -> Unit,
) {
  val vm = viewModel { trendingViewModel() }
  TrendingScreen(
    pager = vm.pager,
    navToDetail = navToDetail,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Inject
@Composable
private fun TrendingScreen(pager: Flow<PagingData<Movie>>, navToDetail: (id: Long) -> Unit) {
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
      pager = pager,
      onMovieClick = { navToDetail(it) },
    )
  }
}

@Preview(name = "Day mode")
@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
  TrendingScreen(
    pager = flowOf(
      PagingData.from(
        listOf(
          Movie(
            position = 0,
            id = 0,
            title = "A very long title that must be wrapped in multiple lines",
            poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
            overview = "Once upon a time...",
            vote_average = 1.2,
            release_date = "2022-02-03",
          ),
          Movie(
            position = 0,
            id = 0,
            title = "A very long title that must be wrapped in multiple lines",
            poster_path = "https://dummyimage.com/500x750/000/fff.jpg",
            overview = "Once upon a time...",
            vote_average = 1.2,
            release_date = "2022-02-03",
          ),
        ),
      ),
    ),
    navToDetail = {},
  )
}
