package com.example.animeapp.screens.animefavourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeFavouriteViewModel : ViewModel() {
    // lager _anime som er en mutablestateflow av Anime
    private val _favouriteAnime = MutableStateFlow<List<Anime>>(emptyList())
    val favouriteAnime = _favouriteAnime.asStateFlow()


    init {
        loadFavourites()
    }


    fun loadFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteAnime.value = LocalAnimeRepository.getFavouriteAnimes()
        }
    }

    fun removeFromFavourites(animeId: Int) {
        viewModelScope.launch {
            val currentList = _favouriteAnime.value
            _favouriteAnime.value = currentList.filterNot { it.id == animeId }

            LocalAnimeRepository.removeFromFavourites(animeId)
        }
    }
}

