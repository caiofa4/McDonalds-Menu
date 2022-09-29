package com.example.mcdonaldsmenu.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mcdonaldsmenu.R
import com.example.mcdonaldsmenu.network.model.Menu

class MenuActivity : AppCompatActivity(), MenuContract.View {

    private val menuViewModel: MenuViewModel by lazy {
        MenuViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }

    override fun onNetworkError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}