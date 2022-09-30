package com.example.mcdonaldsmenu.epoxymodel

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.RoundedCornersTransformation
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.mcdonaldsmenu.R
import com.example.mcdonaldsmenu.network.model.Item

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MenuItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val tvItemName: TextView
    private val ivItem: ImageView
    private val itemContainer: View

    private val cropCircleTransformation by lazy {
        RoundedCornersTransformation(15F)
    }

    private var onMenuItemClickedListener: OnMenuItemClickedListener? = null

    init {
        View.inflate(context, R.layout.module_item_card, this)
        tvItemName = findViewById(R.id.tv_item_name)
        ivItem = findViewById(R.id.iv_item_card)
        itemContainer = findViewById(R.id.cl_item_card)
    }

    @ModelProp
    fun setMenuData(menuModuleData: MenuModuleData) {
        ivItem.load(menuModuleData.item.url) {
            crossfade(true)
            error(R.mipmap.ic_launcher_round)
            transformations(cropCircleTransformation)
        }

        tvItemName.text = menuModuleData.item.name

        itemContainer.setOnClickListener {
            onMenuItemClickedListener?.onItemClicked(menuModuleData.item)
        }
    }

    @CallbackProp
    fun setItemClickListener(listener: OnMenuItemClickedListener?) {
        onMenuItemClickedListener = listener
    }

    interface OnMenuItemClickedListener {
        fun onItemClicked(item: Item)
    }

    data class MenuModuleData(
        val item: Item
    ) : ModuleItem
}
