package com.example.animeapp.screens.animeSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeItem(anime : Anime){
    Column() {
        Text(
            text = "id: " + anime.id.toString()
        )
        Text(
            text = "title: " + anime.title
        )
    }
}