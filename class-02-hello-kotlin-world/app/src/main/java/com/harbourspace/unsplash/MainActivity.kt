package com.harbourspace.unsplash

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

import androidx.compose.ui.res.stringResource

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onCreate")

    enableEdgeToEdge()
    setContent {
      UnsplashTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Greeting(
            name = "World 🌍",
            modifier = Modifier.padding(innerPadding)
          )
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = stringResource(id = R.string.greeting, name),
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  UnsplashTheme {
    Greeting("World 🌍")
  }
}