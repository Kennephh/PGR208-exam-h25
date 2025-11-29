package com.example.animeapp.screens.animefavourites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.animeapp.components.AnimeItem

@Composable
fun AnimeFavouriteScreen(
    viewModel: AnimeFavouriteViewModel
){
    val favourites by viewModel.favouriteAnime.collectAsState()

    LazyColumn{
        items(favourites){ favourite ->
            AnimeItem(anime = favourite)
        }
    }

}