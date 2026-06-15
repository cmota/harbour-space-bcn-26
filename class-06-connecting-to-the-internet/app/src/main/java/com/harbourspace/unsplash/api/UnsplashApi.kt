package com.harbourspace.unsplash.api

import com.harbourspace.unsplash.data.UnsplashItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "kO6m_gqEjnmJCdrg7uCUHirlO-u9soB6TPJ66IYXhGk"

interface UnsplashApi {

  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("photos")
  suspend fun fetchImages() : List<UnsplashItem>
}