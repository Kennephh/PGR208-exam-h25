package com.example.animeapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animeSearch.AnimeSearchViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation(
    //homeViewModel : HomeViewModel,
    animeListViewModel: AnimeListViewModel,
    animeSearchViewModel: AnimeSearchViewModel
) {

    val navController = rememberNavController()
    val navHostController = rememberNavController()

    var activeItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    selected = activeItem == 0,

                )
            }
        }
    ) {

    }

}











