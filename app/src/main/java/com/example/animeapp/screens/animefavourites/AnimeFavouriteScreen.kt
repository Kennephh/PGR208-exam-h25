package com.example.animeapp.screens.animefavourites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeapp.components.AnimeItem

@Composable
fun AnimeFavouriteScreen(
    viewModel: AnimeFavouriteViewModel = viewModel()
){
    val favourites by viewModel.favouriteAnime.collectAsState()

    LaunchedEffect(Unit) { // Kjører loadFavourites når uniten endres
        viewModel.loadFavourites()
    }

    LazyColumn{
        items(favourites){ favourite ->
            AnimeItem(
                anime = favourite,
                onRemoveFavourite = {animeRemove ->
                    viewModel.removeFavourite(animeRemove)
                }
            )

        }
    }

}