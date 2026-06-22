package com.harbourspace.unsplash.repository

import com.harbourspace.unsplash.data.UnsplashItem

class UnsplashRepository(val dao: UnsplashDao) {

  val allImages = dao.getImages()

  fun insertImage(image: UnsplashItem) {
    AppDatabase.databaseExecuters.execute {
      dao.insertImage(image)
    }
  }
}