package com.example.animeapp.screens.animedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.animeapp.components.AnimeDetailsItem

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    onBackClick: () -> Unit,
    viewModel: AnimeDetailsViewModel
) {

    LaunchedEffect(animeId) {
        viewModel.setAnime(animeId)
    }

    val anime by viewModel.anime.collectAsState()
    val isFavourite by viewModel.isFavourite.collectAsState()

    if (anime != null){
        AnimeDetailsItem(
            anime = anime!!,
            isFavourite = isFavourite,
            onFavouriteClick = {viewModel.toggleFavourite()},
            goBack = onBackClick
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            CircularProgressIndicator()
        }
    }

}