package com.example.animeapp.screens.anime

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeapp.components.AnimeItem

@Composable
fun AnimeListScreen(viewModel: AnimeListViewModel = viewModel()){

    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasNextPage by viewModel.hasNextPage.collectAsState()

    LazyColumn {items(animeList) { anime ->
            AnimeItem(anime)
        }
        if (hasNextPage && !isLoading) {
            item {
                LaunchedEffect(Unit) {
                    viewModel.loadMoreAnime()
                }
            }
        }
    }


}