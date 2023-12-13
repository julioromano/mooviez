package net.marcoromano.mooviez.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.AnnotatedString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun Movie(
  state: MovieState,
  modifier: Modifier = Modifier,
) {
  val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  Scaffold(
    modifier = modifier.nestedScroll(behavior.nestedScrollConnection),
    topBar = {
      LargeTopAppBar(
        title = {
          Text(state.movie?.title ?: AnnotatedString("Movie")) // TODO Localise
        },
        navigationIcon = {
          IconButton(onClick = { state.eventSink(MovieEvent.NavBack) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
          }
        },
        scrollBehavior = behavior,
      )
    },
  ) { paddingValues ->
    if (state.isLoading) {
      Box(
        modifier = Modifier
          .padding(paddingValues)
          .fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator(
          modifier = Modifier.fillMaxSize(fraction = 0.5f),
        )
      }
    } else {
      LazyColumn(
        modifier = Modifier.padding(paddingValues),
      ) {
        state.movie?.let {
          item {
            net.marcoromano.mooviez.movie.widgets.Movie(
              movie = it,
            )
          }
        }
      }
    }
  }
}
