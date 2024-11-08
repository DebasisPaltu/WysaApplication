package com.example.wysaappl.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wysaappl.R


class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val movieBackdrop = findViewById<ImageView>(R.id.movie_backdrop)
        val movieReleaseDate = findViewById<TextView>(R.id.movie_release_date)
        val movieOverview = findViewById<TextView>(R.id.movie_overview)

        val title = intent.getStringExtra("movie_title")
        val backdropPath = intent.getStringExtra("movie_backdrop")
        val releaseDate = intent.getStringExtra("movie_release_date")
        val overview = intent.getStringExtra("movie_overview")

        movieTitle.text = title
        movieReleaseDate.text = releaseDate
        movieOverview.text = overview

        // Load the backdrop image using a library like Glide or Picasso
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$backdropPath")
            .into(movieBackdrop)
    }
}

