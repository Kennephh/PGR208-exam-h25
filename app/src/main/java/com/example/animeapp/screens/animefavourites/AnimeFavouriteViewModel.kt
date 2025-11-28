package com.example.animeapp.screens.animefavourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.database.FavouriteAnime
import com.example.animeapp.data.repository.APIAnimeRepository
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeFavouriteViewModel : ViewModel(){
    // lager _anime som er en mutablestateflow av Anime
    private val _favouriteAnime = MutableStateFlow<FavouriteAnime?>(null)

    //anime er stateFlow av _anime fordi
    val anime = _favouriteAnime.asStateFlow()

    fun setAnimeById (id : Int){
        viewModelScope.launch {
            _favouriteAnime.value = LocalAnimeRepository.
        }
    }
}