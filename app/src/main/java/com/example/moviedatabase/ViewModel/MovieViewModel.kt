package com.example.moviedatabase.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.moviedatabase.Model.Result
import com.example.moviedatabase.MovieService.LoadMovie
import com.example.moviedatabase.Repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel constructor(application: Application) : AndroidViewModel(application) {

    companion object {
        const val API_KEY = "949143667e45c64bac460a0211b7a824"
        const val LANGUAGE = "en-US"
        const val PAGE = 1
    }

    private val repository = MovieRepository(application)

    private val movieLiveData = MutableLiveData<Boolean>()

    val filteredMovieList = Transformations.switchMap(movieLiveData) {
        if (it) repository.getAllBookmark()
        else repository.getALlMovies()
    }

    fun getAllBookmark(movie: Boolean) {
        movieLiveData.value = movie
    }

    private fun insertAllMovies(result: List<Result>) {
        viewModelScope.launch {
            repository.insertAllMovie(result)
        }
    }

    fun updateBookmark(result: Result) {
        viewModelScope.launch {
            repository.updateBookmark(result)
        }
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = LoadMovie.instance.getAllMovies(API_KEY, LANGUAGE, PAGE).body()
            if (movie != null) {
                this@MovieViewModel.insertAllMovies(movie.results)
            }
        }
    }

}