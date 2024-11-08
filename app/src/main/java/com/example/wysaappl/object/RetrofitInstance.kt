package com.example.wysaappl.`object`

import com.example.wysaappl.apiservise.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

}