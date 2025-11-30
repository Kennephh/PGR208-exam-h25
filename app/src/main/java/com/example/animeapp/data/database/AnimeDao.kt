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


    // FAVOURITE ANIME QUERY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(anime: FavouriteAnime)

    @Query("SELECT * FROM favourite_anime")
    suspend fun getAllFavourites(): List<FavouriteAnime>

    @Delete
    suspend fun removeFavourite(anime: FavouriteAnime)

    @Query("SELECT * FROM favourite_anime")
    suspend fun getAllFavouriteIds(): List<FavouriteAnime>

    @Query("SELECT COUNT(*) FROM favourite_anime WHERE favouriteId = :id")
    suspend fun isFavourite(id: Int) : Int // Returnerer int men blir brukt som boolean

    @Query("DELETE FROM favourite_anime WHERE favouriteId = :id")
    suspend fun deleteFavouriteById(id: Int)

}