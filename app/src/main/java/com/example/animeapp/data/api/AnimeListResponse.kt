package com.example.animeapp.data.api
import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    val data : List<Anime>,
   // val pagination : Pagination?
)

// SerialzedName da de ikke er private verdier så kan ikke bruke underscore
data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage : Int,

    @SerializedName("has_next_page")
    val hasNextPage : Boolean,

    @SerializedName("current_page")
    val currentPage : Int,
    val items : Items?
)

data class Items(
    val count : Int,
    val total : Int,

    @SerializedName("per_page")
    val perPage : Int
)
