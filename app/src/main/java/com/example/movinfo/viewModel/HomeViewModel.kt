package com.example.movinfo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movinfo.pojo.Genre
import com.example.movinfo.pojo.GenreList
import com.example.movinfo.pojo.Movie
import com.example.movinfo.pojo.MovieList
import com.example.movinfo.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private var randomMovieLiveData = MutableLiveData<Movie>()
    private var topPicksLiveData = MutableLiveData<List<Movie>>()
    private var recentlyAddedLiveData = MutableLiveData<List<Movie>>()

    private var genresLiveData = MutableLiveData<List<Genre>>()
    fun getRandomMovie() {

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

    fun getTopPicksMovies() {
        RetrofitInstance.api.getTopPicksMovies().enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body() != null) {
                    topPicksLiveData.value = response.body()!!.movies
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getRecentlyAdded(){
        RetrofitInstance.api.getRecentlyAdded().enqueue(object  : Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body() != null) {
                    recentlyAddedLiveData.value = response.body()!!.movies
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getGenre() {
        RetrofitInstance.api.getGenre().enqueue(object : Callback<GenreList> {
            override fun onResponse(call: Call<GenreList>, response: Response<GenreList>) {
                response.body()?.let { genreList ->
                    genresLiveData.postValue(genreList.genres)
                }
            }

            override fun onFailure(call: Call<GenreList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomMovieLivedata(): LiveData<Movie> {
        return randomMovieLiveData

    }

    fun observeRecentlyAddedLivedata(): LiveData<List<Movie>> {
        return recentlyAddedLiveData
    }

    fun observeTopPicksMovies(): LiveData<List<Movie>> {
        return topPicksLiveData
    }

    fun observeGenreLiveData(): LiveData<List<Genre>> {
        return genresLiveData
    }
}