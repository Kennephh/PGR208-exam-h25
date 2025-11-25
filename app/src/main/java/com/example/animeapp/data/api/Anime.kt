package com.example.animeapp.data.api

import com.google.gson.annotations.SerializedName
data class Anime(
    // Benytte Serialized name da man ikke skal benytte understrek i variabler som ikke er privat
    @SerializedName("mal_id")
    val id : Int?,
    val title : String?
)
