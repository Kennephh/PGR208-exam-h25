package com.example.animeapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun UserCreatedAnimeEditItem(
    userCreatedAnime: UserCreatedAnime,
    goBack: ( () -> Unit ) ? = null,
    onDeleteClick: () -> Unit,
    onEditClick: (UserCreatedAnime) -> Unit
) {

    var title by remember { mutableStateOf(userCreatedAnime.title) }
    var genre by remember { mutableStateOf(userCreatedAnime.genre) }
    var synopsis by remember { mutableStateOf(userCreatedAnime.synopsis) }

    ElevatedCard(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
            ) {

                if (goBack != null){
                    IconButton(
                        onClick = goBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back!",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Text(
                    text = userCreatedAnime.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = if (goBack != null) 0.dp else 8.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 8.dp
                        )
                )

                IconButton(
                    onClick =  onDeleteClick,
                    modifier = Modifier
                        .weight(0.2f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete user anime",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } // Row end
            // Start Text input
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
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
                    onClick = {
                        val updatedAnime = userCreatedAnime.copy(
                            title = title,
                            genre = genre,
                            synopsis = synopsis
                        )
                        onEditClick(updatedAnime)
                    },
                ){
                    Text("Save")
                }
            } // End text input
        } // End card header
    } // End elevated card
}