package com.example.animeapp.data.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animeapp.data.database.AppDataBase
import com.example.animeapp.data.database.FavouriteAnime
import com.example.animeapp.data.database.UserCreatedAnime
import android.database.SQLException

object LocalAnimeRepository {

    private var _appdatabase : AppDataBase? = null
    private val _animeDao get() = _appdatabase?.animeDao()
        ?: throw IllegalStateException("Database not init")
    fun initializeDatabase(context: Context) {
        if(_appdatabase != null) return
        _appdatabase = Room.databaseBuilder(
            context = context.applicationContext,
            klass = AppDataBase::class.java,
            name = "anime-database"
        )   .fallbackToDestructiveMigration()// Sletter den gamle databasen med oppdatering av database versjons-nummer
            .build()
    }

    suspend fun getAllUserCreatedAnime() : List<UserCreatedAnime> {
        try {
            return _animeDao.getAllAnime()
        } catch (e: Exception) {
            Log.e("getAllUserCreatedAnime func i LocalAnimeRepoCatch", e.toString())
            return emptyList()
        }
    }

    suspend fun insertUserCreatedAnime(anime : UserCreatedAnime) : Long {
        return try {
            _animeDao.insertNewAnime(anime)
        } catch (e: Exception) {
            Log.e("Exception: insertUserCreatedAnime i Repo", e.toString())
            -1L
        }
    }

    // Favourites
    // Legge til
    suspend fun addAnimeToFavourites(id: Int){
            val favourite = FavouriteAnime(favouriteId = id)
            try {
                _animeDao.addFavourite(favourite)
            } catch (e : Exception){
                Log.d("addAnimeToFavourites, LocalRepo fail", e.toString())
            }
        }

    suspend fun getAllFavouriteIds(): List<Int>{
        try {
            return _animeDao.getAllFavouriteIds().map { it.favouriteId }
        }catch (e: Exception){
            Log.d("getFavouriteId, LocalRepo fail", e.toString())
            return emptyList()
        }
    }

    suspend fun isFavourite(id: Int): Boolean {
        try {
            return _animeDao.isFavourite(id) > 0
        } catch (e: Exception) {
            Log.d("isFavourite check, LocalRepo fail",e.toString())
            return false
        }
    }

    suspend fun removeFromFavourites(id : Int){
        val favourite = FavouriteAnime(favouriteId = id)
        try {
            _animeDao.removeFavourite(favourite)
        } catch (e: Exception){
            Log.d("removeFromFavourites", e.toString())
        }
    }
}