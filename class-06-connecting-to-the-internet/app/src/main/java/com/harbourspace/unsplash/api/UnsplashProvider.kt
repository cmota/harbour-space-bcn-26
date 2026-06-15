package com.harbourspace.unsplash.api

import com.harbourspace.unsplash.data.UnsplashItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.unsplash.com"

class UnsplashProvider {

  private val retrofit by lazy {
    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()

    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
      .create<UnsplashApi>()
  }

  fun fetchImages(): Flow<List<UnsplashItem>> = flow {
    emit(retrofit.fetchImages())
  }.flowOn(Dispatchers.IO)
}