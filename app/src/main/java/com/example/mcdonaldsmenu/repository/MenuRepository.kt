package com.example.mcdonaldsmenu.repository

import android.util.Log
import com.example.mcdonaldsmenu.network.api.api

class MenuRepository {

    // Get list of all coins from api

    suspend fun getMenuFromAPI(): String {
        val menuJson = api.getMenu()
        Log.i("TesteMenu", menuJson.toString())
        return menuJson.toString()
    }

}