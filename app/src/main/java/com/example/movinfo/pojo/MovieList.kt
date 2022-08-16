package com.example.movinfo.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>

    ) : Parcelable{
        constructor() : this(mutableListOf())
    }