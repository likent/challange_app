package com.mycompany.my.challenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycompany.my.challenge.BuildConfig
import com.mycompany.my.challenge.R
import com.mycompany.my.challenge.databinding.RowMovieBinding
import com.mycompany.my.challenge.models.Movie

class MovieAdapter: PagedListAdapter<Movie, MovieAdapter.MoviewViewHolder>(
    movieComparator) {

    companion object{
        val movieComparator = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return newItem.id == oldItem.id && newItem.posterPath == oldItem.posterPath && newItem.title == oldItem.title && newItem.voteAverage == oldItem.voteAverage
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        return MoviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MoviewViewHolder(itemBinding: RowMovieBinding): RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(item: Movie){
            binding.movie = item
            Glide.with(binding.root.context)
                .load("${BuildConfig.POSTER_BASE_URL}${item.posterPath}")
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivPoster)
        }
    }
}