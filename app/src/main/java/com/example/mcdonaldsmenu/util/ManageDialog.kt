package com.example.mcdonaldsmenu.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.mcdonaldsmenu.R
import com.example.mcdonaldsmenu.network.model.Item

object ManageDialog {

    private val cropCircleTransformation by lazy {
        RoundedCornersTransformation(15F)
    }

    fun createBottomSheetDialog(activity: Activity, item: Item, dialog: Dialog) {
        val view = activity.layoutInflater.inflate(R.layout.item_layout, null, false)

        val itemImage = view.findViewById<ImageView>(R.id.iv_item)
        val itemName = view.findViewById<TextView>(R.id.it_item_name)
        val itemPrice = view.findViewById<TextView>(R.id.it_item_price)
        val itemDescription = view.findViewById<TextView>(R.id.it_item_description)

        val price = "$${item.price.toString()}"
        itemName.text = item.name
        itemPrice.text = price
        itemDescription.text = item.description

        itemImage.load(item.url) {
            crossfade(true)
            error(R.mipmap.ic_launcher_round)
            transformations(cropCircleTransformation)
        }

        dialog.setContentView(view)
        dialog.show()
    }

}