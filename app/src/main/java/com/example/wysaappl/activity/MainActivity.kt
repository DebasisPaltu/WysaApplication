package com.example.wysaappl.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wysaappl.adapter.MovieAdapter
import com.example.wysaappl.viewmodel.MovieViewModel
import com.example.wysaappl.R


class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var languageSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        languageSpinner = findViewById(R.id.language_spinner)
        val recyclerView: RecyclerView = findViewById(R.id.movies_recycler_view)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        adapter = MovieAdapter(emptyList()) { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_backdrop", movie.backdrop_path)
            intent.putExtra("movie_release_date", movie.release_date)
//            intent.putExtra("movie_overview", movie.spoken_languages)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        movieViewModel.movies.observe(this) { movies ->
            adapter.updateData(movies)

            val languages = movies.flatMap { it.spoken_languages }.distinctBy { it.iso_639_1 }
            val languageNames = languages.map { it.english_name }

            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languageNames)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            languageSpinner.adapter = spinnerAdapter
        }

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedLanguage = parent.getItemAtPosition(position) as String
                filterMoviesByLanguage(selectedLanguage)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        movieViewModel.loadMovies("en", 1) // Default to English
    }

    private fun filterMoviesByLanguage(language: String) {
        movieViewModel.movies.value?.let { movies ->
            val filteredMovies = movies.filter { movie ->
                movie.spoken_languages.any { it.english_name == language }
            }
            adapter.updateData(filteredMovies)
        }
    }
}


