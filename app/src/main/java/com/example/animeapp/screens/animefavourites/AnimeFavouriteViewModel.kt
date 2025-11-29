package com.example.animeapp.screens.animefavourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeFavouriteViewModel : ViewModel() {
    // lager _anime som er en mutablestateflow av Anime
    private val _favouriteAnime = MutableStateFlow<List<Anime>>(emptyList())
    val favouriteAnime = _favouriteAnime.asStateFlow()


    fun loadFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            val favouriteIds = LocalAnimeRepository.getAllFavouriteIds()
            val animeList = mutableListOf<Anime>()

            for (id in favouriteIds) {
                val anime = APIAnimeRepository.getAnimeById(id)
                anime?.let { animeList.add(it) }
            }
            _favouriteAnime.value = animeList
        }
    }

    fun removeFavourite(animeRemove: Anime) {
        viewModelScope.launch {
            val currentList =_favouriteAnime.value
            _favouriteAnime.value = currentList.filterNot { it.id == animeRemove.id }
            withContext(Dispatchers.IO) {
                animeRemove.id?.let { id ->
                    LocalAnimeRepository.removeFromFavourites(id)
                }
            }
            loadFavourites()
        }
    }
}

