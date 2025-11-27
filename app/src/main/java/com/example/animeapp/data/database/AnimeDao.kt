package com.example.animeapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimeDao {
    @Query("SELECT * FROM UserCreatedAnime")
    suspend fun getAllAnime() : List<UserCreatedAnime>

    @Query("SELECT * FROM UserCreatedAnime WHERE id = :id")
    suspend fun getAnimeById(id: Int) : UserCreatedAnime?

    // Legger til ny anime, og sletter rader der det er noe fra før
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAnime(animeList: UserCreatedAnime) : Long

    @Delete
    suspend fun deleteAnime(anime: UserCreatedAnime) : Int

    @Update
    suspend fun updateAnime(anime: UserCreatedAnime) : Int

}