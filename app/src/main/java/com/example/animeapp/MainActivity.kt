package com.example.animeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.data.repository.LocalAnimeRepository
import com.example.animeapp.navigation.AppNavigation
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animesearch.AnimeSearchViewModel
import com.example.animeapp.screens.animecreate.AnimeCreateViewModel
import com.example.animeapp.screens.animefavourites.AnimeFavouriteViewModel
import com.example.animeapp.ui.theme.AnimeAPPTheme

class MainActivity : ComponentActivity() {

    private val _animeSearchViewModel : AnimeSearchViewModel by viewModels()
    private val _animeListViewModel : AnimeListViewModel by viewModels()
    private val _animeCreateViewModel : AnimeCreateViewModel by viewModels()
    private val _animeFavouriteViewModel : AnimeFavouriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LocalAnimeRepository.initializeDatabase(this)

        enableEdgeToEdge()
        setContent {
            AnimeAPPTheme {
                AppNavigation(
                    _animeListViewModel,
                    _animeSearchViewModel,
                    _animeCreateViewModel,
                    _animeFavouriteViewModel,
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimeAPPTheme {
        Greeting("Android")
    }
}