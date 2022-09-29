package com.example.mcdonaldsmenu.ui.menu

import com.example.mcdonaldsmenu.network.model.Menu
import com.example.mcdonaldsmenu.ui.BaseView

interface MenuContract {
    interface View : BaseView {
        fun onMenuLoaded(menuList: List<Menu>)
    }
}