package com.example.animeapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.animeapp.data.api.Anime

@Dao
interface AnimeDao {
    @Query("SELECT * FROM UserCreatedAnime")
    suspend fun getAllAnime() : List<Anime>

    @Query("SELECT * FROM UserCreatedAnime WHERE id = :id")
    suspend fun getAnimeById(id: Int) : Anime?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAnime(animeList: List<Anime>)

    @Delete
    suspend fun deleteAnime(anime: Anime) : Int

    @Update
    suspend fun updateAnime(anime: Anime) : Int

}