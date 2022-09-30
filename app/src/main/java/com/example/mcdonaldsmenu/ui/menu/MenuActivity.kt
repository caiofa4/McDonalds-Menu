package com.example.mcdonaldsmenu.ui.menu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.example.mcdonaldsmenu.R
import com.example.mcdonaldsmenu.databinding.ActivityMainBinding
import com.example.mcdonaldsmenu.epoxymodel.*
import com.example.mcdonaldsmenu.network.model.Item
import com.example.mcdonaldsmenu.network.model.Menu
import com.example.mcdonaldsmenu.util.ManageDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

class MenuActivity : AppCompatActivity(), MenuContract.View {

    private lateinit var mActivity: Activity
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: BottomSheetDialog
    private var menuScreenList: MutableList<ModuleItem> = ArrayList()

    private val menuViewModel: MenuViewModel by lazy {
        MenuViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mActivity = this
        dialog = BottomSheetDialog(this)

        initializeUI()

        menuViewModel.attachView(this)
        menuViewModel.getMenuFromAPI()

        binding.srlMenu.setOnRefreshListener {
            menuScreenList.clear()
            menuViewModel.getMenuFromAPI()
            binding.srlMenu.isRefreshing = false
        }
    }

    private fun initializeUI() {
        // set status bar color as white
        val background = ContextCompat.getDrawable(this, R.color.white)
        window.statusBarColor = ContextCompat.getColor(this,android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }

    override fun onMenuLoaded(menuList: List<Menu>) {
        val sortedMenuList = menuList.sortedBy { it.name }

        menuScreenList.add(0, HeaderItemView.HeaderModuleItem)

        for (menu in sortedMenuList) {
            Log.i("testeMenuMain", menu.name)
            menuScreenList.add(TitleItemView.TitleModuleData(menu.name))
            val itemCardList = mutableListOf<MenuItemView.MenuModuleData>()
            menu.items?.forEach { item ->
                Log.i("testeMenuItem", item.name)
                itemCardList.add(MenuItemView.MenuModuleData(item))
            }
            menuScreenList.add(ItemCardList(itemCardList))
        }

        Log.i("testeMenuCount", menuScreenList.count().toString())

        showMenuData(menuScreenList)
    }

    private data class ItemCardList(val itemCardList: List<MenuItemView.MenuModuleData>) : ModuleItem

    private fun showMenuData(menuScreenList: List<ModuleItem>) {
        binding.rvMenu.withModels {
            menuScreenList.forEachIndexed { _, moduleItem ->
                when (moduleItem) {
                    is HeaderItemView.HeaderModuleItem -> headerItemView {
                        id("header")
                    }
                    is TitleItemView.TitleModuleData -> titleItemView {
                        id("title")
                        titleData(moduleItem)
                    }
                    is ItemCardList -> {
                        val menuCards = mutableListOf<MenuItemViewModel_>()
                        moduleItem.itemCardList.forEach {
                            menuCards.add(
                                MenuItemViewModel_()
                                    .id(it.item.name).menuData(it).itemClickListener(object :
                                        MenuItemView.OnMenuItemClickedListener {
                                        override fun onItemClicked(item: Item) {
                                            ManageDialog.createBottomSheetDialog(mActivity, item, dialog)
                                        }
                                    })
                            )
                        }
                        carousel {
                            id("itemCardList")
                            models(menuCards)
                            numViewsToShowOnScreen(2.15F)
                            Carousel.setDefaultGlobalSnapHelperFactory(null)
                        }
                        binding.rvMenu.smoothScrollToPosition(0)
                    }
                }
            }
        }
    }

    override fun onNetworkError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

}