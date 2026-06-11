package com.harbourspace.unsplash

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onCreate")

    enableEdgeToEdge()
    setContent {
      UnsplashTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
          Column(
            modifier = Modifier
              .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            var count by rememberSaveable { mutableIntStateOf(0) }

            Greeting(
              name = "World (${count}) 🌍",
              modifier = Modifier
                .background(Color.Red),
              onClick = {
                Toast.makeText(this@MainActivity, "Clicked on a button!", Toast.LENGTH_SHORT).show()
              }
            )

            Image(
              painter = painterResource(R.drawable.ic_launcher_foreground),
              contentDescription = stringResource(R.string.greeting)
            )

            TextButton(onClick = {
              count += 1
            } ) {
              Text("+")
            }
          }
        }
      }
    }
  }

  override fun onResume() {
    super.onResume()

    Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onResume")
  }

  override fun onPause() {
    Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onPause")

    super.onPause()
  }

  override fun onDestroy() {
    Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onDestroy")

    super.onDestroy()
  }
}

@Composable
fun Greeting(
  name: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Text(
    text = stringResource(id = R.string.greeting, name),
    modifier = modifier
      .clickable(
        enabled = true,
        onClick = { onClick() }
      )
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  UnsplashTheme {
    Greeting("World 🌍", Modifier, {})
  }
}