package com.example.animeapp.data.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.api.Images
import com.example.animeapp.data.api.Jpg
import com.example.animeapp.data.database.AppDataBase
import com.example.animeapp.data.database.FavouriteAnime
import com.example.animeapp.data.database.UserCreatedAnime

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

    // Tileggsfunksjon
    suspend fun getAllUserCreatedAnimeSort() : List<UserCreatedAnime> {
        try {
            return _animeDao.getAllAnimeSort()
        } catch (e: Exception) {
            Log.e("getAllUserCreatedAnimeSort func i LocalAnimeRepoCatch", e.toString())
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

    suspend fun deleteUserCreatedAnime (anime : UserCreatedAnime){
            _animeDao.deleteAnime(anime)
    }

    suspend fun updateUserCreatedAnime (anime: UserCreatedAnime) : Int {
            return try {
                _animeDao.updateAnime(anime)
            } catch (e : Exception){
                Log.e("Exception: updateUserCreatedAnime", e.toString())
                -1
            }
    }

    // Favourites
    // Legge til
    suspend fun addAnimeToFavourites(anime: Anime){

        val year = anime.year ?: anime.aired?.prop?.from?.year ?: 0
        val entity = FavouriteAnime(
            favouriteId = anime.id ?: 0,
            title = anime.title ?: "Unknown",
            imageUrl = anime.images?.jpg?.largeImageUrl ?: "",
            score = anime.score,
            episodes = anime.episodes,
            year = year
        )

        try {
            _animeDao.addFavourite(entity)
        } catch (e : Exception){
            Log.e("LocalRepo", "Add fav failed", e)
        }
    }

    suspend fun getFavouriteAnimes(): List<Anime>{
        try {
            val entities = _animeDao.getAllFavourites()

            return entities.map { entity ->
                Anime(
                    id = entity.favouriteId,
                    title = entity.title,
                    score = entity.score,
                    episodes = entity.episodes,
                    year = entity.year,
                    synopsis = null,
                    genres = null,
                    studios = null,
                    aired = null,
                    images = Images(
                        jpg = Jpg(largeImageUrl = entity.imageUrl)
                    )
                )
            }
        } catch (e: Exception){
            Log.e("LocalRepo", "Get favs failed", e)
            return emptyList()
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
        try {
            _animeDao.deleteFavouriteById(id)
        } catch (e: Exception){
            Log.d("removeFromFavourites", e.toString())
        }
    }
}