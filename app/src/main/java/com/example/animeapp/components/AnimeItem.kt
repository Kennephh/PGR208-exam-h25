package com.example.animeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.repository.LocalAnimeRepository
import kotlinx.coroutines.launch

@Composable
fun AnimeItem(
    anime: Anime,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    showDetails: (() -> Unit) ? = null
){

    val cardHeight = 80.dp
    val leftShape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
    val borderThickness = 2.dp

    val year = anime.year
        ?: anime.aired?.prop?.from?.year
        ?: "Unknown"

    ElevatedCard(
        shape = leftShape,
        modifier = Modifier
            .padding(all = 4.dp)
            .height(cardHeight),
        onClick = {
            showDetails?.invoke()
        }
    ) {
        // Start main row
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Start Box-1 (Image-box)
            Box(

            ){
                AsyncImage(
                    model = anime.images?.jpg?.largeImageUrl,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(cardHeight)
                        .clip(leftShape)
                        .border(
                            borderThickness,
                            MaterialTheme.colorScheme.primary,
                            leftShape
                        )
                )

                Text(
                    text = "${anime.id}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .sizeIn(20.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(2.dp)
                )
            } // End Box-1

            // Start Box-2 (Title, info and fav-btn)
            Box(
                modifier = Modifier
                    .height(cardHeight)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "$year",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                        Text(
                            text = anime.title ?: "Unknown Title",
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                        )
                        HorizontalDivider(
                            thickness = borderThickness,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Episodes: ${anime.episodes.toString()}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                    }

                    ElevatedButton(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { onFavouriteClick() }
                    ) {
                        Icon(
                            imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Fav icon",
                            tint = if (isFavourite) Color(205,0,0) else MaterialTheme.colorScheme.secondary
                        )
                    }
                }

            } // End Box-2
        } // End main row
    }
}