package com.harbourspace.unsplash.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harbourspace.unsplash.data.UnsplashItem
import kotlinx.coroutines.flow.Flow

@Dao
interface UnsplashDao {

  @Query("SELECT * FROM UnsplashItem")
  fun getImages(): Flow<List<UnsplashItem>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertImage(image: UnsplashItem)

}