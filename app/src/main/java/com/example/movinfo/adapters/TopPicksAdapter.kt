package com.example.movinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movinfo.databinding.TopPicksItemBinding
import com.example.movinfo.pojo.Movie
import com.example.movinfo.util.Constants

class TopPicksAdapter(): RecyclerView.Adapter<TopPicksAdapter.TopPicksViewHolder>() {

    private var movieList = ArrayList<Movie>()

    fun setMovies(movieList:ArrayList<Movie>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class TopPicksViewHolder( val binding:TopPicksItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPicksViewHolder {
        return  TopPicksViewHolder(TopPicksItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopPicksViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(Constants.IMAGE_BASE + movieList[position].poster)
            .into(holder.binding.imgTopPickItem)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}