package com.example.animeapp.components

import android.graphics.Rect
import android.widget.Space
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animeapp.data.api.Anime

@Composable
fun AnimeItem(anime: Anime) {

    val cardShape = RectangleShape
    val cardHeight = 80.dp

    ElevatedCard(
        shape = cardShape,
        modifier = Modifier
            .padding(all = 4.dp)

    ) {
        // Start main row
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Start Box-1
            Box(

            ){
                AsyncImage(
                    model = anime.images.jpg.largeImageUrl,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(cardHeight)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = cardShape
                        )
                )

                Text(
                    text = "${anime.id}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(2.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
            } // End Box-1

            // Start Box-2
            Box(
                modifier = Modifier
                    .weight(1f)
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
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = anime.title ?: "Unknown Title",
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                        )
                        HorizontalDivider()
                        Text(
                            text = "Studio? Episodes?",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    ElevatedButton(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Fav icon",
                            tint = Color(205,0,0)
                        )
                    }

                }


            } // End Box-2
        } // End main row
    }
}