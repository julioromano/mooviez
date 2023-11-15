package net.marcoromano.mooviez.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun UserScore(modifier: Modifier = Modifier, userScore: Int) {
  val text = remember(userScore) {
    buildAnnotatedString {
      withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
        append(userScore.toString())
      }
      withStyle(
        SpanStyle(
          fontSize = 6.sp,
          baselineShift = BaselineShift.Superscript,
        ),
      ) {
        append("%")
      }
    }
  }
  Surface(
    modifier = modifier.clip(CircleShape),
    color = MaterialTheme.colorScheme.onBackground,
  ) {
    Box(
      modifier = Modifier.padding(3.dp),
      contentAlignment = Alignment.Center,
    ) {
      CircularProgressIndicator(
        progress = 1f,
        color = ProgressIndicatorDefaults.circularColor.copy(alpha = 0.5f),
        strokeWidth = 3.dp,
      )
      CircularProgressIndicator(
        progress = userScore / 100f,
        strokeWidth = 3.dp,
      )
      Text(
        text = text,
        color = MaterialTheme.colorScheme.background,
      )
    }
  }
}
