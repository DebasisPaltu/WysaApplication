package com.example.wysaappl.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wysaappl.R
import com.example.wysaappl.model.Movie

class MovieAdapter(private var movies: List<Movie>, private val onItemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    fun updateData(newMovies: List<Movie>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.movie_title)
        private val yearTextView: TextView = itemView.findViewById(R.id.movie_year)
        private val backdropImageView: ImageView = itemView.findViewById(R.id.movie_backdrop)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            yearTextView.text = movie.release_date.take(4) // Show only the year
            Glide.with(backdropImageView.context)
                .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
                .into(backdropImageView)
        }
    }
}


