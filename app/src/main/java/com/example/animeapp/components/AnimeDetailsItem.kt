package com.example.animeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeDetailsItem(
    anime: Anime,
    goBack: ( () -> Unit ) ? = null
) {

    val borderThickness = 2.dp

    ElevatedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                contentAlignment = Alignment.TopCenter
            ){
                AsyncImage(
                    model = anime.images.jpg.largeImageUrl,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .border(
                            borderThickness,
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                )
                Text(
                    text = anime.title ?: "Unknown Title",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 2.5.dp, top = 2.dp, end = 2.5.dp)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                        .padding(4.dp)
                )
            }

            FlowRow(
                //horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Studio: Masters of Anime",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

                Text(
                    text = "Episodes: 74",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

                Text(
                    text = "Year: 1996",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )

            }

            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .fillMaxWidth()

            )

            HorizontalDivider(
                thickness = borderThickness,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question." +
                        "Lot of info on the anime in question.",
                style = MaterialTheme.typography.bodySmall,

                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

        }
    }
}