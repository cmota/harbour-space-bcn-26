package com.harbourspace.unsplash

import android.content.Intent
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.harbourspace.unsplash.utils.EXTRA_IMAGE

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Toast.makeText(this@MainActivity, "onCreate", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onCreate")

    val openDetails = {
      val intent = Intent(this, DetailsActivity::class.java)
      startActivity(intent)
    }

    enableEdgeToEdge()
    setContent {
      UnsplashTheme {
        Scaffold(
          topBar = {
            CenterAlignedTopAppBar(
              title = {
                Text(
                  text = stringResource(R.string.app_name)
                )
              }
            )
          },
          modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
          Column(
            modifier = Modifier.padding(innerPadding),
          ) {

            val images = listOf(
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada,
              R.drawable.sagrada
            )

            var search by rememberSaveable { mutableStateOf("") }

            Log.d(TAG, "debug | search=$search")
            Log.w(TAG, "warning | search=$search")
            Log.e(TAG, "error | search=$search")

            OutlinedTextField(
              value = search,
              onValueChange = { letter ->
                search = letter
              },
              modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
              keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
              )
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
              items(images) {
                Image(
                  painter = painterResource(it),
                  contentDescription = stringResource(R.string.description_sagrada),
                  modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                      openDetails()
                    })
                )
              }
            }
          }
        }
      }
    }
  }
}