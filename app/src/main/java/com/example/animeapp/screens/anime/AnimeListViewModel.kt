package com.example.animeapp.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.APIAnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList = _animeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _hasNextPage = MutableStateFlow(true)
    val hasNextPage = _hasNextPage.asStateFlow()

    private var _currentPage = 1

    init {
        loadMoreAnime()}

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
}