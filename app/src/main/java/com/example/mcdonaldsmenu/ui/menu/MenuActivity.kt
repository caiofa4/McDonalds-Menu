package com.example.mcdonaldsmenu.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mcdonaldsmenu.R

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

    override fun onMenuLoaded(json: String) {
        Toast.makeText(this, json, Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}