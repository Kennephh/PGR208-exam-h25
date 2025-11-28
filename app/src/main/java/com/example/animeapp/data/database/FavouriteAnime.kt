package com.example.animeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="favourite_anime")
data class FavouriteAnime(
    @PrimaryKey
    val favouriteId : Int
)

