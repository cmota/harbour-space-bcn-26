package com.harbourspace.unsplash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.harbourspace.unsplash.data.UnsplashItem
import com.harbourspace.unsplash.repository.AppPreferences
import com.harbourspace.unsplash.ui.AboutScreen
import com.harbourspace.unsplash.ui.FavouritesScreen
import com.harbourspace.unsplash.ui.MainScreen
import com.harbourspace.unsplash.ui.UnsplashViewModel
import com.harbourspace.unsplash.ui.theme.UnsplashTheme
import com.harbourspace.unsplash.utils.EXTRA_IMAGE

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

  private val unsplashViewModel: UnsplashViewModel by viewModels()

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Toast.makeText(this@MainActivity, "onCreate", Toast.LENGTH_SHORT).show()

    Log.d(TAG, "onCreate")

    val openDetails = { image: UnsplashItem ->
      val intent = Intent(this, DetailsActivity::class.java)
      intent.putExtra(EXTRA_IMAGE, image)
      startActivity(intent)
    }

    enableEdgeToEdge()

    val bottomBar = listOf(
      BottomNavigationScreen.Home,
      BottomNavigationScreen.Favourites,
      BottomNavigationScreen.About
    )

    val preferences = AppPreferences(this)

    setContent {
      val preferencesTheme = preferences.isDarkTheme()
      val isDarkTheme = if (preferencesTheme == -1) isSystemInDarkTheme() else preferencesTheme == 1
      val darkTheme = rememberSaveable { mutableStateOf(isDarkTheme) }
      val backstack = remember { mutableStateListOf<Any>(BottomNavigationScreen.Home) }

      UnsplashTheme(
        darkTheme = darkTheme.value
      ) {
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
          bottomBar = {
            NavigationBar {
              bottomBar.forEach {
                NavigationBarItem(
                  selected = backstack.lastOrNull() == it,
                  onClick = {
                    if (backstack.lastOrNull() != it) {
                      backstack.add(it)
                    }
                  },
                  label = {
                    Text(stringResource(it.resId))
                  },
                  icon = {
                    Icon(
                      imageVector = it.icon,
                      contentDescription = stringResource(it.resId)
                    )
                  }
                )
              }
            }
          },
          modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
          Column(
            modifier = Modifier.padding(innerPadding),
          ) {
            NavDisplay(
              backStack = backstack,
              onBack = {
                backstack.removeLastOrNull()
              },
              entryProvider = entryProvider {
                entry<BottomNavigationScreen.Home> {
                  MainScreen(
                    unsplashViewModel = unsplashViewModel,
                    openDetails = openDetails
                  )
                }

                entry<BottomNavigationScreen.Favourites> {
                  FavouritesScreen()
                }

                entry<BottomNavigationScreen.About> {
                  AboutScreen(
                    darkTheme = darkTheme,
                    saveAction = { darkTheme -> preferences.setDarkTheme(darkTheme) }
                  )
                }
              }
            )
          }
        }
      }
    }
  }
}