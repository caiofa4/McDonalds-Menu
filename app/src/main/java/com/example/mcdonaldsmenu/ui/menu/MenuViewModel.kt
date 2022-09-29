package com.example.mcdonaldsmenu.ui.menu

import android.content.Context
import com.example.mcdonaldsmenu.repository.MenuRepository
import com.example.mcdonaldsmenu.ui.BaseViewModel
import kotlinx.coroutines.launch

class MenuViewModel(private val context: Context) : BaseViewModel<MenuContract.View>() {

    private val menuRepository by lazy {
        MenuRepository()
    }

    fun getMenuFromAPI() {
        launch {
            try {
                val menuJson = menuRepository.getMenuFromAPI()
                currentView?.onMenuLoaded(menuJson)
            } catch (ex: Exception) {
                currentView?.onNetworkError(ex.localizedMessage ?: "")
            }
        }
    }
}