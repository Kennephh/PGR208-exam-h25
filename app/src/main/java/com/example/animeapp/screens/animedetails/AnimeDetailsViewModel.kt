package com.example.animeapp.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel: ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)
    val anime = _anime.asStateFlow()

    private val _isFavourite = MutableStateFlow(false)
    val isFavourite = _isFavourite.asStateFlow()

    fun setAnime(animeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            _anime.value = APIAnimeRepository.getAnimeById(animeId)

            _isFavourite.value = LocalAnimeRepository.isFavourite(animeId)

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
                LocalAnimeRepository.addAnimeToFavourites(animeId)
                _isFavourite.value = true
            }
        }
    }

}