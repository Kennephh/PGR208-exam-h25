package com.example.animeapp.data.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animeapp.data.database.AppDataBase
import com.example.animeapp.data.database.FavouriteAnime
import com.example.animeapp.data.database.UserCreatedAnime

object LocalAnimeRepository {

    private lateinit var _appdatabase : AppDataBase
    private val _animeDao by lazy { _appdatabase.animeDao() }

    fun initializeDatabase(context: Context) {
        _appdatabase = Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = "anime-database"
        ).build()
    }

    suspend fun getAllUserCreatedAnime() : List<UserCreatedAnime> {
        try {
            return _animeDao.getAllAnime()
        } catch (e: Exception) {
            Log.d("getAllUserCreatedAnime func i LocalAnimeRepoCatch", e.toString())
            return emptyList()
        }
    }

    suspend fun insertUserCreatedAnime(anime : UserCreatedAnime) : Long {
        return try {
            _animeDao.insertNewAnime(anime)
        } catch (e: Exception) {
            -1L
        }
    }

    // Favourites
    // Legge til
    suspend fun addAnimeToFavourites(id : Int){
        val favourite = FavouriteAnime(favouriteId = id)
        try {
            _animeDao.addFavourite(favourite)
        } catch (e : Exception){
            Log.d("addAnimeToFavourites, LocalRepo fail", e.toString())
        }

    }

    suspend fun getAllFavouriteIds(): List<FavouriteAnime>{
        try {
            return _animeDao.getAllFavouriteIds()
        }catch (e: Exception){
            Log.d("getFavouriteId, LocalRepo dail", e.toString())
            return emptyList()
        }
    }

    suspend fun isFavourite():
}