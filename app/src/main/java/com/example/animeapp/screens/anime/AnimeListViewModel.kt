package com.example.animeapp.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.AnimeListResponse
import com.example.animeapp.data.repository.APIAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _animeList = MutableStateFlow<AnimeListResponse?>(null)
    val animeList = _animeList.asStateFlow()
    fun setAnimeList(){
        viewModelScope.launch {
            _animeList.value = APIAnimeRepository.getAnimeList(page = 1)
        }
    }

    init {setAnimeList()}

}