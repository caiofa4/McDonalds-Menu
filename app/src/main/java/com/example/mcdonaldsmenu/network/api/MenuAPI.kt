package com.example.mcdonaldsmenu.network.api

import com.example.mcdonaldsmenu.network.BASE_MENU_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val menuRetrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_MENU_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val okHttpClient: OkHttpClient by lazy {
    val builder = OkHttpClient.Builder()

    builder.build()
}

val api: API by lazy {
    menuRetrofit.create(API::class.java)
}