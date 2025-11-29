package com.example.animeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun UserCreatedAnimeItem(
    userCreatedAnime: UserCreatedAnime,
    showDetails: (() -> Unit) ? = null
) {

    val cardHeight = 42.dp

    OutlinedCard(
        onClick = {
            showDetails?.invoke()
        },
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = userCreatedAnime.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                )
            }

            Text(
                text = userCreatedAnime.genre,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.5f)
            )

        }
    }
}