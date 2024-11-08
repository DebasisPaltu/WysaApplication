package com.example.wysaappl.repository

import com.example.wysaappl.`object`.RetrofitInstance

class MovieRepository {
    suspend fun getPopularMovies(language: String, page: Int) =
        RetrofitInstance.api.getPopularMovies("780f9e8f07214f79f32c19bf4d1937d9", language, page)

    suspend fun getMovieDetails(movieId: Int) =
        RetrofitInstance.api.getMovieDetails(movieId, "https://api.themoviedb.org/3/")
}