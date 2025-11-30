package com.example.animeapp.screens.anime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeapp.components.AnimeItem

@Composable
fun AnimeListScreen(
    viewModel: AnimeListViewModel = viewModel(),
    onAnimeClick: (Int) -> Unit
){

    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasNextPage by viewModel.hasNextPage.collectAsState()
    val favouriteIds by viewModel.favouriteIds.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavourites()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Title row start
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "All animes in JikanAPI",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        } // Title row end

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(animeList) { anime ->

                val isFavourite = favouriteIds.contains(anime.id)

                AnimeItem(
                    anime = anime,
                    isFavourite = isFavourite,
                    onFavouriteClick = {
                        anime.id?.let { id ->
                            viewModel.toggleFavourite(id)
                        }
                    },
                    showDetails = {
                        anime.id?.let{ id ->
                            onAnimeClick(id)
                        }
                    }
                )

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

}