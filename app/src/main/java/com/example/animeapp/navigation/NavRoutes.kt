package com.example.animeapp.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {

    @Serializable
    object AnimeListRoute : NavRoutes()

    @Serializable
    object AnimeSearchRoute : NavRoutes()

    @Serializable
    object AnimeCreateRoute : NavRoutes()

    @Serializable
    object AnimeFavouriteRoute : NavRoutes()

    @Serializable
    data class AnimeDetailsRoute(
        val animeId: Int
    ) : NavRoutes()

}