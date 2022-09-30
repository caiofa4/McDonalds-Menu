package com.example.mcdonaldsmenu.util

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.mcdonaldsmenu.R
import com.example.mcdonaldsmenu.network.model.Item
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog


object ManageDialog {

    private val cropCircleTransformation by lazy {
        RoundedCornersTransformation(15F)
    }

    private fun makeDialogStateExpanded(dialog: BottomSheetDialog) {
        dialog.setOnShowListener { dial ->
            val d = dial as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun createBottomSheetDialog(activity: Activity, item: Item, dialog: BottomSheetDialog) {
        makeDialogStateExpanded(dialog)

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