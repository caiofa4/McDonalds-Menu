package com.example.mcdonaldsmenu.util

import com.example.mcdonaldsmenu.network.MENUS
import com.example.mcdonaldsmenu.network.model.Menu
import com.google.gson.Gson
import com.google.gson.JsonObject

fun getItemsFromJson(jsonObject: JsonObject): List<Menu> {
    val menuList: MutableList<Menu> = mutableListOf()

    if (jsonObject.has(MENUS)) {
        val rawMenusArray = jsonObject.getAsJsonArray(MENUS)
        rawMenusArray.forEach { jsonElement ->
            val jsonArrayObject = jsonElement.asJsonObject
            val menu = Gson().fromJson(jsonArrayObject, Menu::class.java)
            menuList.add(menu)
        }
    }

    return menuList
}