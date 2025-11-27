package com.example.animeapp.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.animeapp.data.database.Genre
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun AnimeCreateScreen(animeCreateViewModel: AnimeCreateViewModel){
    val animeList by animeCreateViewModel.userCreatedAnimeList.collectAsState()
    animeCreateViewModel.setUserCreatedAnime()

    var newTitle by remember {
        mutableStateOf("")
    }
    var genre by remember {
        mutableStateOf("")
    }

    Column{
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
                if(newTitle != null && genre != null){
                    val newGenre = Genre(0,genre) // Alle har id: 0
                    val newAnime = UserCreatedAnime(name= newTitle, genre= newGenre)
                    animeCreateViewModel.insertUserCreatedAnime(newAnime)
                    newTitle = ""
                    genre = ""
                }
            }
        ) { Text("Add Anime") }
    }
    LazyColumn {
        items(animeList) { anime ->
            Text("Title: ${anime.name}, Genre: ${anime.genre}")
        }
    }
}