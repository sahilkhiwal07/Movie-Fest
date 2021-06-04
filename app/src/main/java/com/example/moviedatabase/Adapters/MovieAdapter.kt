package com.example.moviedatabase.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedatabase.Detailed
import com.example.moviedatabase.Model.Result
import com.example.moviedatabase.R

class MovieAdapter(
    private val context: Context,
    private val onFavClicked: (Result) -> Unit
)
    : ListAdapter<Result, MovieAdapter.MovieHolder>(DIFF_ITEM_CALLBACK) {

    companion object {

        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        getItem(position)?.let { movie ->

            holder.bind(movie)

            holder.favourite.setOnClickListener {
                onFavClicked.invoke(movie)
            }

            holder.singleItem.setOnClickListener {
                context.startActivity(Detailed.sendData(it.context, movie))
            }

        }
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: AppCompatImageView = itemView.findViewById(R.id.movieImage)
        private val movieTitle: AppCompatTextView = itemView.findViewById(R.id.movieTitle)
        private val movieLanguages: AppCompatTextView = itemView.findViewById(R.id.movieLanguage)
        private val movieVotes: AppCompatTextView = itemView.findViewById(R.id.movieVote)
        val favourite: AppCompatImageView = itemView.findViewById(R.id.favourite)
        val singleItem: CardView = itemView.findViewById(R.id.singleItems)

        fun bind(result: Result) {
            movieTitle.text = result.title
            movieLanguages.text = result.original_language
            movieVotes.text = result.vote_count.toString()

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + result.poster_path)
                .into(image)

            if (result.isSelected) {
                favourite.setImageResource(R.drawable.ic_fill_heart)
            } else {
                favourite.setImageResource(R.drawable.ic_heart)
            }

        }

    }

}