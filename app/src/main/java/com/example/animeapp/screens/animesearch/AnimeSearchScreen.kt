package com.example.animeapp.screens.animesearch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AnimeSearchScreen(animeSearchViewModel: AnimeSearchViewModel) {
    var id by remember {
        mutableStateOf<String>("")
    }

    val anime = animeSearchViewModel.anime.collectAsState()


    Column() {
        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Id") }
        )
        Button(
            onClick = {
                val idParsed = id.toIntOrNull()
                if (idParsed != null) {
                    animeSearchViewModel.setAnimeById(idParsed)
                }
            }
        ) {
            Text("Søk etter anime")
        }
        anime.value?.let {
            AnimeItem(it)
        } ?: Text("Søk for å vise anime")
    }
}