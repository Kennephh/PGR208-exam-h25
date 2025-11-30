package com.example.animeapp.screens.animesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
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

    private val _isFavourite = MutableStateFlow(false)
    val isFavourite = _isFavourite.asStateFlow()

    fun setAnimeById (id : Int){
        viewModelScope.launch(Dispatchers.IO) {

            _isLoading.value = true
            _anime.value = null

            try {
                _anime.value = APIAnimeRepository.getAnimeById(id)

                if (_anime.value != null) {
                    _isFavourite.value = LocalAnimeRepository.isFavourite(id)
                }


            } catch (e: Exception) {
                _anime.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavourite(){
        val currentAnime = _anime.value ?: return
        val animeId = currentAnime.id ?: return

        viewModelScope.launch(Dispatchers.IO){
            if (_isFavourite.value) {
                LocalAnimeRepository.removeFromFavourites(animeId)
                _isFavourite.value = false
            } else {
                LocalAnimeRepository.addAnimeToFavourites(currentAnime)
                _isFavourite.value = true
            }
        }
    }

}