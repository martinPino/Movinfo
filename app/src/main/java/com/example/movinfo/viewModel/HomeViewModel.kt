package com.example.movinfo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movinfo.pojo.Movie
import com.example.movinfo.pojo.MovieList
import com.example.movinfo.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var randomMovieLiveData = MutableLiveData<Movie>()
    private var topPicksLiveData = MutableLiveData<List<Movie>>()
    fun getRandomMovie(){

        RetrofitInstance.api.getMovieDetails().enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body() != null) {


                    val randomMovie: Movie = response.body()!!.movies.random()
                    randomMovieLiveData.value = randomMovie
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getTopPicksMovies(){
        RetrofitInstance.api.getTopPicksMovies().enqueue(object : Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if(response.body() != null){
                    topPicksLiveData.value = response.body()!!.movies
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun observeRandomMovieLivedata():LiveData<Movie>{
        return  randomMovieLiveData

    }

    fun observeTopPicksMovies():LiveData<List<Movie>>{
        return topPicksLiveData
    }
}