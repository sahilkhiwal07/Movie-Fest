package com.example.moviedatabase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviedatabase.Model.Result
import kotlinx.android.synthetic.main.detailed.*

class Detailed : AppCompatActivity() {

    private lateinit var result: Result

    companion object {

        const val MY_DATA = "MY_DATA"

        fun sendData(context: Context, result: Result): Intent {
            val intent = Intent(context, Detailed::class.java)
            intent.putExtra(MY_DATA, result)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed)

        result = intent.getParcelableExtra<Result>(MY_DATA)!!

        setDataToDetailedActivity()

    }

    private fun setDataToDetailedActivity() {
        movieTitle.text = result.title
        movieAdult.text = result.adult.toString()
        Language.text = result.original_language
        originalTitle.text = result.original_title
        movieOverView.text = result.overview
        movieDate.text = result.release_date
        moviePopularity.text = result.popularity.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + result.poster_path)
            .into(moviePoster)

    }

}