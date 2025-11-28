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
import com.example.animeapp.components.AnimeSearchCard

@Composable
fun AnimeSearchScreen(animeSearchViewModel: AnimeSearchViewModel) {
    var id by remember {mutableStateOf<String>("")}
    val anime by animeSearchViewModel.anime.collectAsState()
    var lastSearchId by remember { mutableStateOf<Int?>(null) }


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
                    lastSearchId = idParsed
                }
            }
        ) {
            Text("Search to show Anime")
        }
        //
        anime?.let { AnimeSearchCard(it) }
            ?: run {
                if(lastSearchId != null){
                    Text("Anime with id $lastSearchId can not be found")
                } else Text("Search to show Anime")
            }
    }
}