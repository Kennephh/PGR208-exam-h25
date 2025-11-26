package com.example.animeapp.screens.animesearch

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeItem(anime : Anime){

    Column(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .border(
                    width = 2.dp,
                    Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "id: " + anime.id.toString()
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "title: " + anime.title
            )
        }
    }


}