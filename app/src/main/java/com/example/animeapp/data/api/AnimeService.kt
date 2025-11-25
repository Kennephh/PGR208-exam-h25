package com.example.animeapp.data.api

import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Sørget for å importere retrofit2 sin respons, ikke okhttp3
interface AnimeService {
    // Hent en anime etter ide
    @GET("anime/{id}/full")
    suspend fun getAnimeById(
        @Path("id") id : Int
    ) : Response<AnimeData>

    // Hent flere animer, legger også til flere kall til tileggsfunksjoner; sortering
    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page: Int = 1,
        @Query("q") query: String? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("sort") sort: String? = "desc"
    ): Response<List<Anime?>>
}