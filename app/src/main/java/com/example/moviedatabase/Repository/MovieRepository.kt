package com.example.moviedatabase.Repository

import android.app.Application
import com.example.moviedatabase.Dao.MovieDao
import com.example.moviedatabase.Database.MovieDatabase
import com.example.moviedatabase.Model.Result

class MovieRepository(application: Application) {

    private val movieDao: MovieDao

    init {
        val db = MovieDatabase.getDatabase(application)
        movieDao = db.movieDao()
    }

    fun getALlMovies() = movieDao.getAllMovies()

    fun getAllBookmark() = movieDao.getFavourite()

    suspend fun insertAllMovie(result: List<Result>) = movieDao.insertAllMovie(result)

    suspend fun updateBookmark(result: Result) {
        val id = result.id
        val test: Boolean = movieDao.updateBookmark(id)
        if (test) movieDao.isNotSelected(id)
        else movieDao.isSelected(id)

    }

}