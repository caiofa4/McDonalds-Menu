package com.example.mcdonaldsmenu.network.api

import com.google.gson.JsonObject
import retrofit2.http.GET

interface API {

    @GET("menu")
    suspend fun getMenu(): JsonObject
}