package com.example.animeapp.screens.animecreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.components.UserCreatedAnimeItem
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun AnimeCreateScreen(animeCreateViewModel: AnimeCreateViewModel){

    val animeList by animeCreateViewModel.userCreatedAnimeList.collectAsState()

    LaunchedEffect(Unit) {
        animeCreateViewModel.setUserCreatedAnime()
    }

    var title by remember {
        mutableStateOf("")
    }
    var genre by remember {
        mutableStateOf("")
    }
    var synopsis by remember {
        mutableStateOf("")
    }

    fun addNewAnime(){
        if(title.isNotBlank() && genre.isNotBlank() && synopsis.isNotBlank()){
            val trimmedTitle = title.trim()
            val trimmedGenre = genre.trim()
            val trimmedSynopsis = synopsis.trim()

            //val newGenre = Genre(0,trimmedGenre) // Alle har id: 0
            val newAnime = UserCreatedAnime(
                title = trimmedTitle,
                genre = trimmedGenre,
                synopsis = trimmedSynopsis
            )

            animeCreateViewModel.insertUserCreatedAnime(newAnime)

            title = ""
            genre = ""
            synopsis = ""
        }
    }

    // Main start
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Title row start
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Add new anime",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        } // Title row end

        // Search bar start
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ){

            OutlinedTextField(
                value = title,
                onValueChange = {title = it},
                label = {
                    Text(
                        text = "Enter title..",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = genre,
                onValueChange = {genre = it},
                label = {
                    Text(
                        text = "Enter genre..",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = synopsis,
                onValueChange = {synopsis = it},
                label = {
                    Text(
                        text = "Enter synopsis..",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {addNewAnime()},
            ){
                Text("Add")
            }
        } // Search bar end

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Animes you have added",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 32.dp)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(animeList) { anime ->
                UserCreatedAnimeItem(anime)
            }
        }
    } // Main end
}