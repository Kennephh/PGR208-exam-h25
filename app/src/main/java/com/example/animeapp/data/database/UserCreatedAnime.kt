package com.example.animeapp.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

//Dette er hva brukeren bruker for å lagre sine egen anime lokalt // Tabellen
@Entity
data class UserCreatedAnime(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    // Room støtter ikke å lagre et helt objekt i en kolonne, dette falter det ut automatisk
     @Embedded
    val genre : Genre
)
data class Genre(
    val genreId : Int,
    val genreName : String
)