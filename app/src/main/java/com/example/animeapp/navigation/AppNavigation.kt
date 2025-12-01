package com.example.animeapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.components.UserCreatedAnimeEditItem
import com.example.animeapp.components.UserCreatedAnimeDetailsItem
import com.example.animeapp.screens.anime.AnimeListScreen
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animecreate.AnimeCreateScreen
import com.example.animeapp.screens.animecreate.AnimeCreateViewModel
import com.example.animeapp.screens.animedetails.AnimeDetailsScreen
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
                    label = { Text("Anime List") },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
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
                    label = { Text("Create") },
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
                    label = { Text("Favourites") },
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
                        animeCreateViewModel,
                        onAnimeClick = { anime ->
                            animeCreateViewModel.onUserAnimeSelected(anime)
                            navController.navigate(NavRoutes.UserAnimeDetailRoute)
                        },
                        onDeleteClick = {anime -> animeCreateViewModel.deleteUserCreatedAnime(anime)}
                    )
                }
                composable<NavRoutes.AnimeFavouriteRoute>{
                    AnimeFavouriteScreen(
                        animeFavouriteViewModel,
                        onAnimeClick = { animeId ->
                            animeListViewModel.onAnimeSelected(animeId)
                            navController.navigate(NavRoutes.AnimeDetailRoute)
                        }
                    )
                }
                composable<NavRoutes.AnimeDetailRoute>{ backStackEntry ->
                    val animeId = animeListViewModel.selectedAnimeId

                    if (animeId != null) {
                        AnimeDetailsScreen(
                            animeId = animeId,
                            onBackClick = { navController.popBackStack() },
                            viewModel = animeDetailsViewModel
                        )
                    }

                }
                composable<NavRoutes.UserAnimeDetailRoute>{
                    val selectedAnime = animeCreateViewModel.selectedUserAnime

                    if (selectedAnime != null){
                        UserCreatedAnimeDetailsItem(
                            anime = selectedAnime,
                            goBack = {navController.popBackStack()},
                            onDeleteClick = {
                                animeCreateViewModel.deleteUserCreatedAnime(selectedAnime)
                                navController.popBackStack()
                            },
                            onEditClick =  { animeToEdit ->
                                animeCreateViewModel.onUserAnimeSelected(animeToEdit)
                                navController.navigate(NavRoutes.UserCreatedAnimeEditRoute)
                            }
                        )
                    } else {
                        Text("Could not find anime details")
                    }
                }

                composable<NavRoutes.UserCreatedAnimeEditRoute>{
                    val animeToEdit = animeCreateViewModel.selectedUserAnime

                    if(animeToEdit != null){
                        UserCreatedAnimeEditItem(
                            userCreatedAnime = animeToEdit,
                            goBack = {navController.popBackStack()},
                            onDeleteClick = {
                                animeCreateViewModel.deleteUserCreatedAnime(animeToEdit)
                                navController.navigate(NavRoutes.AnimeCreateRoute){
                                    popUpTo(NavRoutes.AnimeCreateRoute){inclusive = true}
                                }
                            },
                            onEditClick = { updatedAnime ->
                                // 1. Be ViewModel om å oppdatere databasen
                                animeCreateViewModel.updateUserCreatedAnime(updatedAnime)
                                // 2. Gå tilbake til create skjerm
                                navController.navigate(NavRoutes.AnimeCreateRoute)
                            }
                        )
                    }
                }
            }
        }
    }
}











