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

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setAnimeById (id : Int){
        viewModelScope.launch {

            _isLoading.value = true
            _anime.value = null

            try {
                _anime.value = APIAnimeRepository.getAnimeById(id)
            } catch (e: Exception) {
                _anime.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}