package com.example.moviedatabase.MovieService

import com.example.moviedatabase.Model.Movies
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieInterface {

    @GET("movie/top_rated")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    )
            : Response<Movies>

}


object LoadMovie {

    val instance: MovieInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit.create(MovieInterface::class.java)

    }
}

