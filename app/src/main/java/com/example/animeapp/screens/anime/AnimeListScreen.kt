package com.example.animeapp.screens.anime

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun AnimeListScreen(animeListViewModel: AnimeListViewModel){

    val animeList = animeListViewModel.animeList.collectAsState().value

    animeList?.data?.let { animeItems ->
        LazyColumn {
            items(animeItems) { anime ->
                Text(
                    text = "${anime.id} - ${anime.title}"
                )
            }
        }
    } ?: run {
        Text("Det laster, vent litt")
    }

}
