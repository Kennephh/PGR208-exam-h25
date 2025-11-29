package com.example.animeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.data.database.UserCreatedAnime

@Composable
fun UserCreatedAnimeItem(userCreatedAnime: UserCreatedAnime) {

    val cardHeight = 24.dp
    val borderThickness = 2.dp

    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
    ){

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {

            Text(
                text = userCreatedAnime.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
            )

            VerticalDivider(
                thickness = borderThickness,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(cardHeight)
            )

            Text(
                text = userCreatedAnime.genre,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(0.5f)
            )

        }
    }
}