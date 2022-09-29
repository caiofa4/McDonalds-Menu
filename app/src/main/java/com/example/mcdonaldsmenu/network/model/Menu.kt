package com.example.mcdonaldsmenu.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("items")
    val items: List<Item>? = null

) : Parcelable