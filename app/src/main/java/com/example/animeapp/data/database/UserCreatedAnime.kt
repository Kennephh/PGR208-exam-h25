package com.example.animeapp.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

//Dette er hva brukeren bruker for å lagre sine egen anime lokalt // Tabellen
@Entity
data class UserCreatedAnime(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val genre: String,
    val synopsis: String
)
