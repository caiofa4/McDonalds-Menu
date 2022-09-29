package com.example.mcdonaldsmenu.repository

import com.example.mcdonaldsmenu.network.api.api
import com.example.mcdonaldsmenu.network.model.Menu
import com.example.mcdonaldsmenu.util.getItemsFromJson

class MenuRepository {

    // Get list of all coins from api

    suspend fun getMenuFromAPI(): List<Menu> {
        val menuJson = api.getMenu()
        return getItemsFromJson(menuJson)
    }

}