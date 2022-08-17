package com.example.movinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movinfo.databinding.TopPicksItemBinding
import com.example.movinfo.pojo.Movie
import com.example.movinfo.util.Constants

class RecentlyAddedAdapter: RecyclerView.Adapter<RecentlyAddedAdapter.RecentlyViewHolder>() {
    private var recentlyList = ArrayList<Movie>()

    fun setRecentlyAddedMovies(recentlyList:ArrayList<Movie>){
        this.recentlyList = recentlyList
        notifyDataSetChanged()
    }

    class RecentlyViewHolder( val binding: TopPicksItemBinding ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewHolder {
        return  RecentlyViewHolder(TopPicksItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecentlyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(Constants.IMAGE_BASE + recentlyList[position].poster)
            .into(holder.binding.imgTopPickItem)    }

    override fun getItemCount(): Int {
        return recentlyList.size    }
}