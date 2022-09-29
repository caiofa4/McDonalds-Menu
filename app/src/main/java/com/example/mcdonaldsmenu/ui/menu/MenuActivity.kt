package com.example.mcdonaldsmenu.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mcdonaldsmenu.databinding.ActivityMainBinding
import com.example.mcdonaldsmenu.epoxymodel.*
import com.example.mcdonaldsmenu.network.model.Menu

class MenuActivity : AppCompatActivity(), MenuContract.View {

    private lateinit var binding: ActivityMainBinding
    private var menuScreenList: MutableList<ModuleItem> = ArrayList()

    private val menuViewModel: MenuViewModel by lazy {
        MenuViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuViewModel.attachView(this)
        menuViewModel.getMenuFromAPI()
    }

    override fun onMenuLoaded(menuList: List<Menu>) {
        if (menuList.isNotEmpty()) {
            val message1 = """
                ${menuList[0].name}
                ${menuList[0].items?.get(0)?.name}
                ${menuList[0].items?.get(0)?.price}
            """.trimIndent()

            val message2 = """
                ${menuList[1].name}
                ${menuList[1].items?.get(0)?.name}
                ${menuList[1].items?.get(0)?.price}
            """.trimIndent()

            //Toast.makeText(this, message1, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, message2, Toast.LENGTH_SHORT).show()
        }

        menuScreenList.add(0, HeaderItemView.HeaderModuleItem)

        menuList.forEach { menu ->
            menuScreenList.add(TitleItemView.TitleModuleData(menu.name))
        }


        showMenuData(menuScreenList)
    }

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
                }
            }

        }
    }

    override fun onNetworkError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }


}