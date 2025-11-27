package com.example.animeapp.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun AnimeCreateScreen(animeCreateViewModel: AnimeCreateViewModel){
    val anime = animeCreateViewModel.userCreatedAnimeList.collectAsState()
    animeCreateViewModel.setUserCreatedAnime()

    var newTitle by remember {
        mutableStateOf("")
    }

    Column{

    }
}