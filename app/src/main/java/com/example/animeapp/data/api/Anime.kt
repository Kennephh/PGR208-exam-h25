package com.example.animeapp.data.api

import com.google.gson.annotations.SerializedName
data class Anime(
    // Benytte Serialized name da man ikke skal benytte understrek i variabler som ikke er privat
    @SerializedName("mal_id")
    val id : Int?,
    val title : String?,
    val images : Images
)

data class AnimeData(
    val data : Anime
)

data class AnimeListResponse(
    val data : List<Anime>,
    val pagination : Pagination?
)

// SerializedName da de ikke er private verdier så kan ikke bruke underscore
data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage : Int,

    @SerializedName("has_next_page")
    val hasNextPage : Boolean,

    @SerializedName("current_page")
    val currentPage : Int,
    val items : Items?
)

data class Images(
    val jpg : Jpg
)

data class Jpg(
    @SerializedName("large_image_url")
    val largeImageUrl : String?
)
data class Items(
    val count : Int,
    val total : Int,

    @SerializedName("per_page")
    val perPage : Int
)