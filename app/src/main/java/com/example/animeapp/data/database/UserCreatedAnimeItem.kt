package com.example.animeapp.data.database

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

@Composable
fun UserCreatedAnimeItem(userCreatedAnime: UserCreatedAnime) {

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
                text = "Id: " + userCreatedAnime.id
            )

            Text(
                text = "Navn: " + userCreatedAnime.name
            )

            Text(
                text = "Genre: " + userCreatedAnime.genre
            )
        }
    }

}