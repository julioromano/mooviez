package net.marcoromano.mooviez.trending.widgets.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder
import net.marcoromano.mooviez.widgets.UserScore

@Composable
internal fun MoviePlaceHolder() {
  Column(
    modifier = Modifier
      .padding(8.dp)
      .width(160.dp),
  ) {
    Box {
      Column {
        Box(
          modifier = Modifier
            .placeholder(true)
            .aspectRatio(500f / 750f)
            .clip(RoundedCornerShape(32f)),
        )
        Spacer(modifier = Modifier.height(32.dp))
      }
      UserScore(
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(8.dp),
        userScore = 0,
      )
    }
    Column(
      modifier = Modifier.padding(8.dp),
    ) {
      Text(
        text = "Lorem ipsum lorem ipsum",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.placeholder(true),
      )
      Spacer(
        modifier = Modifier.height(4.dp),
      )
      Text(
        modifier = Modifier.placeholder(true),
        text = "1999-01-01",
      )
    }
  }
}

@Preview
@Composable
private fun Preview() {
  MoviePlaceHolder()
}
