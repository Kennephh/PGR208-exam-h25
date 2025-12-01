package com.example.animeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="favourite_anime")
data class FavouriteAnime(
    @PrimaryKey
    val favouriteId : Int,
    val title: String,
    val imageUrl: String,
    val score: Double?,
    val episodes: Int?,
    val year: Int?
)