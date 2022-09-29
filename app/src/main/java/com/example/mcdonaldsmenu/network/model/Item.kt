package com.example.mcdonaldsmenu.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("url")
    val url: String? = null

) : Parcelable