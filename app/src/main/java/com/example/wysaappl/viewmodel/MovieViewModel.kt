package com.example.wysaappl.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wysaappl.repository.MovieRepository
import com.example.wysaappl.model.Movie
import com.example.wysaappl.model.MovieDetails
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun loadMovies(language: String, page: Int) {
        viewModelScope.launch {
            try {
                val movieResponse = repository.getPopularMovies(language, page)
                _movies.value = movieResponse.results
                Log.d("MovieViewModel", "Movies loaded: ${movieResponse.results.size}")
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error loading movies", e)
            }
        }
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = repository.getMovieDetails(movieId)
        }
    }
}