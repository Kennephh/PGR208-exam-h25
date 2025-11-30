package com.example.animeapp.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList = _animeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _hasNextPage = MutableStateFlow(true)
    val hasNextPage = _hasNextPage.asStateFlow()

    var selectedAnimeId: Int? = null
        private set

    private var _currentPage = 1

    private val _favouriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favouriteIds: StateFlow<Set<Int>> = _favouriteIds.asStateFlow()

    init {
        loadFavourites()
        loadMoreAnime()
    }

    fun onAnimeSelected(id: Int) {
        selectedAnimeId = id
    }

    fun loadMoreAnime(){
        if(_isLoading.value || !_hasNextPage.value ) return
        viewModelScope.launch {
            _isLoading.value = true
            val result = APIAnimeRepository.getAnimeList(page = _currentPage)
            val newAnime = result?.data.orEmpty()

            _animeList.value = if (_currentPage == 1) newAnime else _animeList.value + newAnime

            _hasNextPage.value = result?.pagination?.hasNextPage == true

            _currentPage++

            _isLoading.value = false
        }
    }

    fun toggleFavourite(animeId: Int){

        viewModelScope.launch(Dispatchers.IO){
            val currentFavourites = _favouriteIds.value.toMutableSet()

            if (currentFavourites.contains(animeId)){
                LocalAnimeRepository.removeFromFavourites(animeId)
                currentFavourites.remove(animeId)
            } else {

                val anime = _animeList.value.find { it.id == animeId}

                if (anime != null) {
                    LocalAnimeRepository.addAnimeToFavourites(anime)
                    currentFavourites.add(animeId)
                }
            }
            _favouriteIds.value = currentFavourites

        }
    }

    fun loadFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            val ids = LocalAnimeRepository.getAllFavouriteIds()
            _favouriteIds.value = ids.toSet()
        }
    }
}