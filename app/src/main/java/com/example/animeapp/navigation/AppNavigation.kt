package com.example.animeapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.screens.anime.AnimeListViewModel
import com.example.animeapp.screens.animeSearch.AnimeSearchViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animeapp.screens.anime.AnimeListScreen
import com.example.animeapp.screens.animeSearch.AnimeSearchScreen
import com.example.animeapp.screens.home.HomeScreen
import com.example.animeapp.screens.home.HomeViewModel


@Composable
fun AppNavigation(
    homeViewModel : HomeViewModel,
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
                    onClick = {
                        activeItem = 0
                        navController.navigate(NavRoutes.HomeRoute)
                    },
                    label = { Text("Home") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Hjem-skjerm ikon"
                        )
                    }
                )// Home end

                NavigationBarItem(
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AnimeListRoute)
                    },
                    label = { Text("List") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Annsikt ikon"
                        )
                    }
                )// AnimeList end

                NavigationBarItem(
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.AnimeSearchRoute)
                    },
                    label = { Text("Søk") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Hjem-skjerm ikon"
                        )
                    }
                )// AnimeSearch end
            }// NavigationBar end

        }
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.HomeRoute
            ) {
                composable<NavRoutes.HomeRoute> {
                    HomeScreen(
                        homeViewModel
                    )
                }
                composable<NavRoutes.AnimeListRoute> {
                    AnimeListScreen(
                        animeListViewModel
                    )
                }
                composable<NavRoutes.AnimeSearchRoute> {
                    AnimeSearchScreen(
                        animeSearchViewModel
                    )
                }
            }
        }

    }

}











