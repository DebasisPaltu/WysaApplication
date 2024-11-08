package com.example.wysaappl.model

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val backdrop_path: String?,
    val spoken_languages: List<Language> // Add this to hold available languages
)

data class Language(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class MovieResponse(
    val results: List<Movie>
)

data class MovieDetails(
    val title: String,
    val release_date: String,
    val backdrop_path: String,
    val overview: String,
    val vote_average: Float,
    val original_language: String
)
