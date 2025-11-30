package com.example.animeapp.screens.animecreate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.database.UserCreatedAnime
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.SQLException


class AnimeCreateViewModel : ViewModel() {
    private val _userCreatedAnimeList = MutableStateFlow<List<UserCreatedAnime>>(emptyList())
    val userCreatedAnimeList = _userCreatedAnimeList.asStateFlow()

    var selectedUserAnime : UserCreatedAnime? = null
        private set

    fun onUserAnimeSelected(anime: UserCreatedAnime){
        selectedUserAnime = anime
    }

    fun setUserCreatedAnime(){
        viewModelScope.launch(Dispatchers.IO){
            _userCreatedAnimeList.value = LocalAnimeRepository.getAllUserCreatedAnime()
        }
    }

    fun insertUserCreatedAnime(anime : UserCreatedAnime){
        viewModelScope.launch(Dispatchers.IO){
            val newAnimeId = LocalAnimeRepository.insertUserCreatedAnime(anime)
            if(newAnimeId != -1L){
                val newAnime = anime.copy(id = newAnimeId.toInt())
                _userCreatedAnimeList.value += newAnime
            } else{
                Log.e("AnimeCreate", "Failed to save anime")
                throw SQLException("Lagring av anime feilet.")
            }
        }
    }

    fun deleteUserCreatedAnime(anime: UserCreatedAnime){
        viewModelScope.launch ( Dispatchers.IO ){
            LocalAnimeRepository.deleteUserCreatedAnime(anime)
        }
    }
}