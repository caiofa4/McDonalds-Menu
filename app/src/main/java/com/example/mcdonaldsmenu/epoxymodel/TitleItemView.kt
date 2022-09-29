package com.example.mcdonaldsmenu.epoxymodel

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.mcdonaldsmenu.R
import java.math.BigDecimal

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TitleItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val tvTitle: TextView

    init {
        View.inflate(context, R.layout.module_title, this)
        tvTitle = findViewById(R.id.tv_title)
    }

    @ModelProp
    fun setTitleData(titleModuleData: TitleModuleData) {
        tvTitle.text = titleModuleData.title
    }

    data class TitleModuleData(val title: String) : ModuleItem
}