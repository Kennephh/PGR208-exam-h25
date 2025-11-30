package com.example.animeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserCreatedAnime::class,
        FavouriteAnime::class
               ],
    version = 5,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun animeDao() : AnimeDao
}
