package com.example.animeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animeapp.data.api.Anime

@Database(
    entities = [UserCreatedAnime::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun animeDao() : AnimeDao
}
