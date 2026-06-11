package com.harbourspace.unsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

class DetailsActivity: ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      UnsplashTheme() {
        Scaffold() { innerPadding->
          Column(
            modifier = Modifier.padding(innerPadding)
          ) {
            Image(
              painter = painterResource(R.drawable.sagrada),
              modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
              contentScale = ContentScale.FillWidth,
              contentDescription = stringResource(R.string.description_sagrada)
            )

            val modifier = Modifier
              .weight(1.0f)
              .padding(16.dp)

            Line(
              modifier = modifier,
              cell1ResId = R.string.image_camera,
              cell1Value = "NIKON D3200",

              cell2ResId = R.string.image_aperture,
              cell2Value = "f 1/50",
            )

            Line(
              modifier = modifier,
              cell1ResId = R.string.image_focal_length,
              cell1Value = "NIKON D3200",

              cell2ResId = R.string.image_aperture,
              cell2Value = "f 1/50",
            )

            Line(
              modifier = modifier,
              cell1ResId = R.string.image_camera,
              cell1Value = "NIKON D3200",

              cell2ResId = R.string.image_aperture,
              cell2Value = "f 1/50",
            )

            HorizontalDivider(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              thickness = 2.dp,
              color = Color.LightGray
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ) {
              Row(
                modifier = Modifier.weight(1.0f)
              ) {
                Cell(
                  R.string.image_views,
                  "100",
                  modifier = Modifier,
                  horizontalArrangement = Arrangement.Center,
                  Alignment.CenterHorizontally
                )
              }

              Row(
                modifier = Modifier.weight(1.0f)
              ) {
                Cell(
                  R.string.image_downloads,
                  "150",
                  modifier = Modifier,
                  horizontalArrangement = Arrangement.Center,
                  Alignment.CenterHorizontally
                )
              }

              Row(
                modifier = Modifier.weight(1.0f)
              ) {
                Cell(
                  R.string.image_likes,
                  "4000",
                  modifier = Modifier,
                  horizontalArrangement = Arrangement.Center,
                  Alignment.CenterHorizontally
                )
              }
            }
          }
        }
      }
    }
  }

  @Composable
  fun Line(
    modifier: Modifier,
    @StringRes cell1ResId: Int,
    cell1Value: String,

    @StringRes cell2ResId: Int,
    cell2Value: String,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth()
    ) {
      Cell(
        resId = cell1ResId,
        value = cell1Value,
        modifier = modifier
      )

      Cell(
        resId = cell2ResId,
        value = cell2Value,
        modifier = modifier
      )
    }
  }

  @Composable
  fun Cell(
    @StringRes resId: Int,
    value: String,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
  ) {
    Row(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = horizontalArrangement
    ) {
      Column(
        horizontalAlignment = horizontalAlignment
      ) {
        Text(
          text = stringResource(resId),
          fontWeight = FontWeight.Bold
        )

        Text(
          text = value
        )
      }
    }
  }
}