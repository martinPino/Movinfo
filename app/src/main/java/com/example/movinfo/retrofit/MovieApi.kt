package com.example.movinfo.retrofit

import com.example.movinfo.pojo.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/3/movie/popular?api_key=0c208068ebcce9842564c387f16cfdbd")
    fun getMovieDetails():Call<MovieList>

    @GET("/3/movie/top_rated?api_key=0c208068ebcce9842564c387f16cfdbd")
    fun getTopPicksMovies(): Call<MovieList>
}