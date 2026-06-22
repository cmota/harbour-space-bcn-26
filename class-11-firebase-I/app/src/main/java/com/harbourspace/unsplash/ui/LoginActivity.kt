package com.harbourspace.unsplash.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.harbourspace.unsplash.MainActivity
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.ui.theme.Typography
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

private const val TAG = "LoginActivity"

class LoginActivity : ComponentActivity() {

  private val sessionViewModel: SessionViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()

    Log.d(TAG, "Current user=${Firebase.auth.currentUser}")

    setContent {
      UnsplashTheme {
        Scaffold {
          Column(
            modifier = Modifier
              .fillMaxSize()
              .padding(it)
              .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {

            Text(
              text = stringResource(R.string.app_name),
              style = Typography.titleLarge
            )

            Spacer(modifier = Modifier.height(72.dp))

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
              value = username,
              onValueChange = { text -> username = text},
              modifier = Modifier.fillMaxWidth(),
              placeholder = {
                Text(
                  text = stringResource(R.string.login_username),
                  color = Color.Unspecified.copy(alpha = 0.5f)
                )
              }
            )

            Spacer(modifier = Modifier.height(8.dp))

            var showPassword by remember { mutableStateOf(false)}

            val authenticated = sessionViewModel.authenticated.collectAsStateWithLifecycle()

            OutlinedTextField(
              value = password,
              onValueChange = { text -> password = text},
              modifier = Modifier.fillMaxWidth(),
              placeholder = {
                Text(
                  text = stringResource(R.string.login_password),
                  color = Color.Unspecified.copy(alpha = 0.5f)
                )
              },
              trailingIcon = {
                IconButton(
                  onClick = {
                    showPassword = !showPassword
                  }
                ) {
                  Icon(
                    imageVector = if (showPassword) {
                      Icons.Default.Visibility
                    } else {
                      Icons.Default.VisibilityOff
                    },
                    contentDescription = stringResource(R.string.description_password)
                  )
                }
              },
              visualTransformation =
                if (showPassword) {
                  VisualTransformation.None
                } else {
                  PasswordVisualTransformation()
                },
              isError = authenticated.value is UiState.Error
            )

            when(authenticated.value) {
              is UiState.Error -> {
                Text(
                  text = (authenticated.value as UiState.Error).message,
                  color = MaterialTheme.colorScheme.error,
                  modifier = Modifier.fillMaxWidth()
                )
              }

              is UiState.Success<*> -> {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
              }

              else -> {
                // Do nothing
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.End
            ) {
              TextButton(
                onClick = {
                  sessionViewModel.createAccount(
                    username = username,
                    password = password
                  )
                }
              ) {
                Text(stringResource(R.string.login_register))
              }

              Spacer(modifier = Modifier.width(16.dp))

              Button(
                onClick = {
                  sessionViewModel.loginAccount(
                    username = username,
                    password = password
                  )
                }
              ) {
                Text(stringResource(R.string.login_app))
              }
            }
          }
        }
      }
    }
  }
}