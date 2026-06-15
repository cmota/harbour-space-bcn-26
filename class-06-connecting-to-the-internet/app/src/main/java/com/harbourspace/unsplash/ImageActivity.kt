package com.harbourspace.unsplash

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.harbourspace.unsplash.ui.theme.UnsplashTheme
import com.harbourspace.unsplash.utils.EXTRA_IMAGE

class ImageActivity: ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val image = intent.getIntExtra(EXTRA_IMAGE, 0)
    if (image == 0) {
      Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
      finish()
      return
    }

    setContent {
      UnsplashTheme {
        Scaffold {
          Column(
            modifier = Modifier
              .fillMaxSize()
              .padding(it)
          ) {
            Image(
              painter = painterResource(image),
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.FillHeight,
              contentDescription = stringResource(R.string.description_sagrada)
            )
          }
        }
      }
    }
  }
}