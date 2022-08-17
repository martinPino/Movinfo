package com.example.movinfo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movinfo.activities.MovieActivity
import com.example.movinfo.adapters.GenresAdapter
import com.example.movinfo.adapters.RecentlyAddedAdapter
import com.example.movinfo.adapters.TopPicksAdapter
import com.example.movinfo.databinding.FragmentHomeBinding
import com.example.movinfo.pojo.Movie
import com.example.movinfo.util.Constants.Companion.IMAGE_BASE
import com.example.movinfo.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMovie: Movie
    private lateinit var  topPicksAdapter: TopPicksAdapter
    private lateinit var genreAdapter: GenresAdapter
    private lateinit var recentlyAdapter : RecentlyAddedAdapter

    companion object{
        const val MOVIE_ID = "com/example/movinfo/fragments/idMovie"
        const val MOVIE_NAME = "com/example/movinfo/fragments/nameMovie"
        const val MOVIE_POSTER = "com/example/movinfo/fragments/posterMovie"
        const val MOVIE_DATE = "com/example/movinfo/fragments/genreMovie"
        const val MOVIE_OVERVIEW = "com/example/movinfo/fragments/overviewMovie"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

        recentlyAdapter = RecentlyAddedAdapter()
        topPicksAdapter = TopPicksAdapter()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        homeMvvm.getRandomMovie()
        observerRandomMovie()
        onRandomMovieClick()

        prepareTopPicksRecyclerview()
        homeMvvm.getTopPicksMovies()
        observeTopPicksLivdata()

        prepareGenreRecyclerView()
        homeMvvm.getGenre()
        observeGenreLivedata()

        prepareRecentlyAddedRecyclerview()
        homeMvvm.getRecentlyAdded()
        observeRecentlyAddedLivedata()


    }



    private fun prepareRecentlyAddedRecyclerview() {
        recentlyAdapter = RecentlyAddedAdapter()
        binding.recRecentlyAdded.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentlyAdapter
        }
    }

    private fun prepareGenreRecyclerView() {
        genreAdapter = GenresAdapter()
        binding.recGenres.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }
    }

    private fun observeGenreLivedata() {
        homeMvvm.observeGenreLiveData().observe(viewLifecycleOwner, Observer { genres->
                genreAdapter.setGenreList(genres)
        })
    }

    private fun prepareTopPicksRecyclerview() {
        topPicksAdapter = TopPicksAdapter()
        binding.recViewTopPicks.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topPicksAdapter
        }
    }
    private fun observeRecentlyAddedLivedata() {
        homeMvvm.observeRecentlyAddedLivedata().observe(viewLifecycleOwner) { recentlyAdded ->

            recentlyAdapter.setRecentlyAddedMovies(recentlyList = recentlyAdded as ArrayList<Movie>)
        }
    }
    private fun observeTopPicksLivdata() {
        homeMvvm.observeTopPicksMovies().observe(viewLifecycleOwner
        ) { movieList ->

            topPicksAdapter.setMovies(movieList = movieList as ArrayList<Movie>)

        }
    }

    private fun onRandomMovieClick() {
        binding.imgRandomMovie.setOnClickListener {
            val intent = Intent(activity, MovieActivity::class.java)
            intent.putExtra(MOVIE_ID, randomMovie.id)
            intent.putExtra(MOVIE_NAME, randomMovie.title)
            intent.putExtra(MOVIE_DATE, randomMovie.releaseDate)
            intent.putExtra(MOVIE_OVERVIEW, randomMovie.overview)
            intent.putExtra(MOVIE_POSTER, randomMovie.poster)

            startActivity(intent)
        }
    }

    private fun observerRandomMovie() {
        homeMvvm.observeRandomMovieLivedata().observe(viewLifecycleOwner
        ) { movie ->
            Glide.with(this@HomeFragment)
                .load(IMAGE_BASE + movie!!.poster)
                .into(binding.imgRandomMovie)
            this.randomMovie = movie
        }
    }


}