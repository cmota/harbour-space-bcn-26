package com.harbourspace.unsplash.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harbourspace.unsplash.data.converters.UrlConverter
import com.harbourspace.unsplash.data.converters.UserConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class UnsplashItem(
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    //val current_user_collections: List<CurrentUserCollection?>?,
    val description: String?,
    val height: Int?,
    @PrimaryKey
    val id: String,
    //val links: Links?,
    val updated_at: String?,
    @field:TypeConverters(UrlConverter::class)
    val urls: Urls?,
    @field:TypeConverters(UserConverter::class)
    val user: User?,
    val width: Int?
): Parcelable