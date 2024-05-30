package com.mbl.deneme_newsapi.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    var id: String = "no_id",
    @SerializedName("name")
    var name: String = "no_name"
) : Parcelable