package com.example.animeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeapp.navigation.AppNavigation
import com.example.animeapp.screens.anime.AnimeListScreen
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animeSearch.AnimeSearchViewModel
import com.example.animeapp.screens.animeSearch.AnimeSearchScreen
import com.example.animeapp.screens.home.HomeViewModel
import com.example.animeapp.ui.theme.AnimeAPPTheme

class MainActivity : ComponentActivity() {

    private val _homeViewModel : HomeViewModel by viewModels()

    private val _animeSearchViewModel : AnimeSearchViewModel by viewModels()

    private val _animeListViewModel : AnimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeAPPTheme {
                AppNavigation(
                    _homeViewModel,
                    _animeListViewModel,
                    _animeSearchViewModel
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