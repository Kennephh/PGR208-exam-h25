package com.example.animeapp.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animeapp.data.database.Genre
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun AnimeCreateScreen(animeCreateViewModel: AnimeCreateViewModel){
    val animeList by animeCreateViewModel.userCreatedAnimeList.collectAsState()

    LaunchedEffect(Unit) {
        animeCreateViewModel.setUserCreatedAnime()
    }

    var newTitle by remember {
        mutableStateOf("")
    }
    var genre by remember {
        mutableStateOf("")
    }

    // Hoved Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text("Add Anime")

            TextField(
                value = newTitle,
                onValueChange = {newTitle = it},
                label = {Text("Title")}
            )

            TextField(
                value = genre,
                onValueChange = {genre = it},
                label = {Text("Genre")}
            )

            Button(
                onClick = {
                    if(newTitle.isNotBlank() && genre.isNotBlank()){
                        val trimmedTitle = newTitle.trim()
                        val trimmedGenre = genre.trim()

                        val newGenre = Genre(0,trimmedGenre) // Alle har id: 0
                        val newAnime = UserCreatedAnime(name= trimmedTitle, genre= newGenre)
                        animeCreateViewModel.insertUserCreatedAnime(newAnime)
                        newTitle = ""
                        genre = ""
                    }
                }
            ) {

                Text("Add Anime")
            }
        }

        LazyColumn(
        ) {
            items(animeList) { anime ->
                Text("Title: ${anime.name}, Genre: ${anime.genre}")
            }
        }
    }
}