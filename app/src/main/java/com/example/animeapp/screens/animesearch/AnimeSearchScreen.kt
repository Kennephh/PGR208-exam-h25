package com.example.animeapp.screens.animesearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.components.AnimeDetailsItem


@Composable
fun AnimeSearchScreen(animeSearchViewModel: AnimeSearchViewModel) {

    var id by remember {mutableStateOf<String>("")}
    val anime by animeSearchViewModel.anime.collectAsState()
    var lastSearchId by remember { mutableStateOf<Int?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLoading by animeSearchViewModel.isLoading.collectAsState()

    fun searchAnimeById(){
        val idParsed = id.toIntOrNull()
        if (idParsed != null){
            animeSearchViewModel.setAnimeById(idParsed)
            lastSearchId = idParsed
            keyboardController?.hide()
        }
    }

    // Start main
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        // Title row start
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Search Anime by ID",
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {

            OutlinedTextField(
                value = id,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        id = input
                    }
                },
                label = {
                    Text(
                        text = "Enter id..",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {searchAnimeById()}
                ),
                modifier = Modifier
                    .weight(1f)
            )

            Button(
                onClick = {searchAnimeById()},
            ) {
                Text("Search")
            }
        } // Search bar end

        // Result box start
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }


                anime != null -> {
                  Box(
                      modifier = Modifier
                          .align(Alignment.TopCenter)
                  ){
                      AnimeDetailsItem(anime!!)
                  }
                }

                lastSearchId != null -> {
                  Text(
                      text = "Anime with id $lastSearchId cannot be found",
                      style = MaterialTheme.typography.bodyLarge,
                      color = MaterialTheme.colorScheme.error
                  )
                }

                else -> {
                    Text(
                        text = "Enter id to start",
                        color = Color.Gray
                    )
                }
            }
        } // Result box end

    } // End main
}