package com.example.animeapp.data.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animeapp.data.database.AppDataBase
import com.example.animeapp.data.database.UserCreatedAnime
import java.sql.SQLException

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
        } catch (e: SQLException) {
            Log.e("SQLException: getAllUserCreatedAnime i Repo", e.toString())
            return emptyList()
        } catch (e: Exception) {
            Log.e("getAllUserCreatedAnime func i LocalAnimeRepoCatch", e.toString())
            return emptyList()
        }
    }

    suspend fun insertUserCreatedAnime(anime : UserCreatedAnime) : Long {
        return try {
            _animeDao.insertNewAnime(anime)
        } catch (e: SQLException) {
            Log.e("SQLException: insertUserCreatedAnime i Repo", e.toString())
            -1L
        }
    }
}