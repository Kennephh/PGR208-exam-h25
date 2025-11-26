package com.example.animeapp.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    object HomeRoute : NavRoutes()

    @Serializable
    object AnimeListRoute : NavRoutes()

    @Serializable
    object AnimeSearchRoute : NavRoutes()
}