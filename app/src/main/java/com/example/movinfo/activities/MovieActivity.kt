package com.example.movinfo.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.movinfo.R
import com.example.movinfo.databinding.ActivityMovieBinding
import com.example.movinfo.fragments.HomeFragment
import com.example.movinfo.util.Constants

class MovieActivity : AppCompatActivity() {
    private lateinit var movieId: String
    private lateinit var movieName: String
    private lateinit var moviePoster: String
    private lateinit var movieDate: String
    private lateinit var movieOverview: String

    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMovieInfo()
        setInformationInViews()


    }

    private fun setInformationInViews() {

        Glide.with(applicationContext)
            .load(Constants.IMAGE_BASE + moviePoster)
            .into(binding.imgMovieDetail)

        binding.collapsingToolbar.title = movieName
        binding.tvArea.text = movieDate
        binding.tvInstructions.text = movieOverview

        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
        binding.collapsingToolbar.setExpandedTitleColor(Color.WHITE)
    }

    private fun getMovieInfo() {
        val intent = intent
        movieId = intent.getStringExtra(HomeFragment.MOVIE_ID)!!
        movieDate = intent.getStringExtra(HomeFragment.MOVIE_DATE)!!
        movieName = intent.getStringExtra(HomeFragment.MOVIE_NAME)!!
        movieOverview = intent.getStringExtra(HomeFragment.MOVIE_OVERVIEW)!!
        moviePoster = intent.getStringExtra(HomeFragment.MOVIE_POSTER)!!

    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory2.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYt.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory2.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYt.visibility = View.VISIBLE
    }
}