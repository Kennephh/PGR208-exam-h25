package com.example.animeapp.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.AnimeListData
import com.example.animeapp.data.repository.APIAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {

    private val _anime = MutableStateFlow<AnimeListData?>(null)

    val anime = _anime.asStateFlow()

    fun setAnime() {
        viewModelScope.launch {
            _anime.value = APIAnimeRepository.getAnimeList()
        }
    }

    init {
        setAnime()
    }


}
