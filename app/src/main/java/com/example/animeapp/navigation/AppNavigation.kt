package com.example.animeapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.components.AnimeDetailsItem
import com.example.animeapp.screens.anime.AnimeListScreen
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animecreate.AnimeCreateScreen
import com.example.animeapp.screens.animecreate.AnimeCreateViewModel
import com.example.animeapp.screens.animedetails.AnimeDetailsViewModel
import com.example.animeapp.screens.animefavourites.AnimeFavouriteScreen
import com.example.animeapp.screens.animefavourites.AnimeFavouriteViewModel
import com.example.animeapp.screens.animesearch.AnimeSearchScreen
import com.example.animeapp.screens.animesearch.AnimeSearchViewModel

@Composable
fun AppNavigation(
    animeListViewModel: AnimeListViewModel,
    animeSearchViewModel: AnimeSearchViewModel,
    animeCreateViewModel: AnimeCreateViewModel,
    animeFavouriteViewModel: AnimeFavouriteViewModel,
    animeDetailsViewModel: AnimeDetailsViewModel
) {

    val navController = rememberNavController()
    var activeItem by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            NavigationBar() {

                NavigationBarItem(
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AnimeListRoute)
                    },
                    label = { Text("Anime") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Ansikt ikon"
                        )
                    }
                )// AnimeList end

                NavigationBarItem(
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.AnimeSearchRoute)
                    },
                    label = { Text("Search") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Søk-skjerm ikon"
                        )
                    }
                )// AnimeSearch end

                NavigationBarItem(
                    selected = activeItem == 3,
                    onClick = {
                        activeItem = 3
                        navController.navigate(NavRoutes.AnimeCreateRoute)
                    },
                    label = { Text("Ideas") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Lag-skjerm ikon"
                        )
                    }
                )// AnimeCreate end
                NavigationBarItem(
                    selected = activeItem == 4,
                    onClick = {
                        activeItem = 4
                        navController.navigate(NavRoutes.AnimeFavouriteRoute)
                    },
                    label = { Text("Favorites") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Lik-skjerm ikon"
                        )
                    }
                )// AnimeFavourite end

            }// NavigationBar end
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.AnimeListRoute
            ) {
                composable<NavRoutes.AnimeListRoute> {
                    AnimeListScreen(
                        animeListViewModel,
                        onAnimeClick = { animeId ->
                            animeListViewModel.onAnimeSelected(animeId)
                            navController.navigate(NavRoutes.AnimeDetailRoute)
                        }
                    )
                }
                composable<NavRoutes.AnimeSearchRoute> {
                    AnimeSearchScreen(
                        animeSearchViewModel
                    )
                }
                composable<NavRoutes.AnimeCreateRoute>{
                    AnimeCreateScreen(
                        animeCreateViewModel
                    )
                }
                composable<NavRoutes.AnimeFavouriteRoute>{
                    AnimeFavouriteScreen(
                        animeFavouriteViewModel
                    )
                }
                composable<NavRoutes.AnimeDetailRoute>{
                    val selectedId = animeListViewModel.selectedAnimeId
                    val animeList by animeListViewModel.animeList.collectAsState()
                    val selectedAnime = animeList.find {it.id == selectedId}

                    if (selectedAnime != null){
                        AnimeDetailsItem(
                            selectedAnime,
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    } else {
                        Text("Could not find anime")
                    }
                }
            }
        }
    }
}











