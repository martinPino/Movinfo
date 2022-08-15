package com.example.movinfo.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/{movie_id}")
    fun getMovieDetails()
}