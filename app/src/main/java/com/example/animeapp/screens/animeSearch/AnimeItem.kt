package com.example.animeapp.screens.animeSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeItem(anime : Anime){
    Column() {
        AsyncImage(
            model = anime.images.jpg.largeImageUrl,
            contentDescription = anime.title,
            modifier = Modifier.size(180.dp)
        )
        Text(
            text = "id: " + anime.id.toString()
        )
        Text(
            text = "title: " + anime.title
        )
    }
}