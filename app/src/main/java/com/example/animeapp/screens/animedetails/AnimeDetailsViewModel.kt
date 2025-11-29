package com.example.animeapp.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel: ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)

    val anime = _anime.asStateFlow()

    fun setAnime(animeId: Int) {
        viewModelScope.launch {
            _anime.value = APIAnimeRepository.getAnimeById(animeId)
        }
    }
}