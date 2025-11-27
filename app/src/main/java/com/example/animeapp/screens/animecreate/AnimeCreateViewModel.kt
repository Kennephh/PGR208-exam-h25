package com.example.animeapp.screens.animecreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeapp.data.database.AnimeDao
import com.example.animeapp.data.database.AppDataBase
import com.example.animeapp.data.database.UserCreatedAnime
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AnimeCreateViewModel : ViewModel() {
    private val _userCreatedAnimeList = MutableStateFlow<List<UserCreatedAnime>>(emptyList())
    val userCreatedAnimeList = _userCreatedAnimeList.asStateFlow()

    fun setUserCreatedAnime(){
        viewModelScope.launch(Dispatchers.IO){
            _userCreatedAnimeList.value = LocalAnimeRepository.getAllUserCreatedAnime() // Ken lager denne i repo
        }
    }

    fun insertUserCreatedAnime(anime : UserCreatedAnime){
        viewModelScope.launch(Dispatchers.IO){
            val newAnimeId = LocalAnimeRepository.insertUserAnime(anime) // Ken funksjon i repo
            if(newAnimeId != 1L){
                val newAnime = anime.copy(id = newAnimeId.toInt())
                _userCreatedAnimeList.value += newAnime
            } else{
                null // Må legge til feilhåndtering
            }

        }
    }
}