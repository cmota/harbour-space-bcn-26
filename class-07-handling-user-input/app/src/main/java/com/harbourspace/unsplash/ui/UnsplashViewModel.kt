package com.harbourspace.unsplash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbourspace.unsplash.api.UnsplashProvider
import com.harbourspace.unsplash.data.UnsplashItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnsplashViewModel: ViewModel() {

  private val _images = MutableStateFlow<List<UnsplashItem>>(emptyList())
  val images: StateFlow<List<UnsplashItem>> = _images.asStateFlow()

  init {
    fetchImage()
  }

  fun fetchImage() {
    val provider = UnsplashProvider()
    viewModelScope.launch {
      provider.fetchImages()
        .collect { images ->
          _images.value = images
        }
    }
  }
}