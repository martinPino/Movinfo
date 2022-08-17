package com.example.movinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movinfo.databinding.GenreItemBinding
import com.example.movinfo.pojo.Genre
import com.example.movinfo.pojo.GenreList

class GenresAdapter():RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private var genreList = ArrayList<Genre>()
    fun setGenreList(genreList: List<Genre>){
        this.genreList = genreList as ArrayList<Genre> /* = java.util.ArrayList<com.example.movinfo.pojo.Genre> */
        notifyDataSetChanged()
    }
    inner class GenresViewHolder(val binding: GenreItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        return GenresViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.binding.tvGenre.text = genreList[position].name
    }

    override fun getItemCount(): Int {
        return genreList.size
    }
}