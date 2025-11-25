package com.example.animeapp.data.repository

import android.util.Log
import com.example.animeapp.data.api.Anime
import com.example.animeapp.data.api.AnimeListResponse
import com.example.animeapp.data.api.AnimeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIAnimeRepository {

    // Lager en http client som skriver ut all nettverkstrafikk til loggene slik at man kan se hva som skjer
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor (
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    // Bygger opp retrofit og setter inn loggførings clienten, setter base url til API og JSON skal konverteres automatisk med GSON
    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    // Retrofit lager en en client av animeservice, som kan brukes til å kjøre http kall
    private val _animeService = _retrofit.create(AnimeService::class.java)

    // Vi bruker AnimeService sin metode og sammenligner id som ble sendt med id i API, hvis det finnes så viser vi respons
    suspend fun getAnimeById(id : Int) : Anime? {
        try {
            val response = _animeService.getAnimeById(id)
            return if(response.isSuccessful){
                response.body()?.data
            } else{
                null
            }
        } catch (e: Exception){
            Log.d("Catch getById", e.message.toString())
            return null
        }
    }

    suspend fun getAnimeList(
        page: Int = 1,
        // query: String? = null,
        // orderBy : String? = null,
        // sort : String? = "desc"
    ): AnimeListResponse?{
        try {
            val response = _animeService.getAnimeList(
                page = page,
                // query = query,
                // orderBy = orderBy,
                // sort = sort
            )
        return if (response.isSuccessful){
            response.body()
        } else{
            return null
        }

        }catch (e: Exception){
            Log.d("Catch getAnimeList", e.message.toString())
            return null
        }
    }

}