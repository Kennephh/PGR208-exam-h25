package com.example.animeapp.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeDetailsItem(
    anime: Anime,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    goBack: ( () -> Unit ) ? = null
) {

    val borderThickness = 2.dp
    val context = LocalContext.current

    val year = anime.year
        ?: anime.aired?.prop?.from?.year
        ?: "Unknown"

    ElevatedCard(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
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
                text = anime.title ?: "Unknown Title",
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
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {



            Box(

            ){
                AsyncImage(
                    model = anime.images?.jpg?.largeImageUrl,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .border(
                            borderThickness,
                            MaterialTheme.colorScheme.primary,
                        )
                )

                ElevatedButton(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(40.dp)
                        .align(Alignment.BottomEnd),
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

            FlowRow(
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 8.dp)
                    .fillMaxWidth()
            ) {
                // Score
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Score",
                        tint = Color(255,235,0),
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                    )
                    Text(
                        text = "${anime.score ?: "N/A"}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }

                // Year
                Text(
                    text = "$year",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

                // Episodes
                Text(
                    text = "Episodes: ${anime.episodes ?: "N/A"}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

                // Studio
                Text(
                    text = "Studio: ${anime.studios?.firstOrNull()?.name ?: "Unknown"}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

                // Genre
                anime.genres?.forEach { genre ->

                    val genreName = genre.name ?: return@forEach

                    Text(
                        text = genreName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .background(
                                MaterialTheme.colorScheme.tertiary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                }

            }

            HorizontalDivider(
                thickness = borderThickness,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = anime.synopsis ?: "No synopsis available",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    anime.id?.let { id ->
                        val url = "https://myanimelist.net/anime/$id"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Info"
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Text(
                    "Read more at MyAnimeList"
                )
            }

        }
    }
}