package com.example.animeapp.screens.animesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Klassen arver fra viewmodel
class AnimeSearchViewModel : ViewModel(){
    // lager _anime som er en mutablestateflow av Anime
    private val _anime = MutableStateFlow<Anime?>(null)

    //anime er stateFlow av _anime fordi
    val anime = _anime.asStateFlow()

    fun setAnimeById (id : Int){
        viewModelScope.launch {
            _anime.value = APIAnimeRepository.getAnimeById(id)
        }
    }
}