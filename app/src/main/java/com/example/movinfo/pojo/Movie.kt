package com.example.movinfo.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,


    ) : Parcelable{
    constructor():this("","","","","")
}