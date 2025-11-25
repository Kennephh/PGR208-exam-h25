package com.example.animeapp.screens.anime

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.animeapp.screens.animeSearch.AnimeItem

@Composable
fun AnimeListScreen(animeListViewModel: AnimeListViewModel){

    val animeList = animeListViewModel.animeList.collectAsState()


    LazyColumn() {
        items(animeList.value){ anime ->
            AnimeItem(anime)

        }
    }
        Button(
            onClick = {

            }
        ) {
            Text(
                text = "Last inn flere"
            )
        }


}
