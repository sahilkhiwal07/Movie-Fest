package com.example.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedatabase.Adapters.MovieAdapter
import com.example.moviedatabase.ViewModel.MovieViewModel
import com.example.moviedatabase.ViewModel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this, MyFactory(application)).get(MovieViewModel::class.java)
    }

    private val adapter by lazy {
        MovieAdapter(this) {
            viewModel.updateBookmark(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        getViewModel()
        getButtons()

    }

    private fun getButtons() {
        btn_switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.getAllBookmark(isChecked)
        }
        viewModel.getAllBookmark(false)
    }

    private fun getViewModel() {

        viewModel.filteredMovieList.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.makeApiCall()
    }

    private fun setRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}