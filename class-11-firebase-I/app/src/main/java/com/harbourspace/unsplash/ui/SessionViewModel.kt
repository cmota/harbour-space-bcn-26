package com.harbourspace.unsplash.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.data.UnsplashItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "SessionViewModel"

class SessionViewModel(application: Application): AndroidViewModel(application) {

  private val _authenticated = MutableStateFlow<UiState<String>>(UiState.Loading)
  val authenticated: StateFlow<UiState<String>> = _authenticated.asStateFlow()

  fun createAccount(
    username: String,
    password: String
  ) {
    if (username.isEmpty() || password.isEmpty()) {
      _authenticated.value = UiState.Error(application.getString(R.string.login_error_empty))
      return
    }

    Firebase.auth.createUserWithEmailAndPassword(
      username,
      password
    ).addOnCompleteListener { response ->
      if (response.isSuccessful) {
        Log.d(TAG, "Account created with success")
        _authenticated.value = UiState.Success("")
      } else {
        _authenticated.value = UiState.Error(response.exception.toString())
      }
    }
  }

  fun loginAccount(
    username: String,
    password: String
  ) {
    if (username.isEmpty() || password.isEmpty()) {
      _authenticated.value = UiState.Error(application.getString(R.string.login_error_empty))
      return
    }

    Firebase.auth.signInWithEmailAndPassword(
      username,
      password
    ).addOnCompleteListener { response ->
      if (response.isSuccessful) {
        Log.d(TAG, "User login with success")
        _authenticated.value = UiState.Success("")
      } else {
        _authenticated.value = UiState.Error(response.exception?.message ?: "")
      }
    }
  }

}