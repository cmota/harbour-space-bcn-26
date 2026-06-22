package com.harbourspace.unsplash.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harbourspace.unsplash.data.UnsplashItem
import java.util.concurrent.Executors

@Database(entities = [UnsplashItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

  abstract fun unsplashDao(): UnsplashDao

  companion object {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    val databaseExecuters = Executors.newFixedThreadPool(2)

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?:
      synchronized(this) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "unsplash_db").build()
        INSTANCE = db
        db
      }
    }
  }
}