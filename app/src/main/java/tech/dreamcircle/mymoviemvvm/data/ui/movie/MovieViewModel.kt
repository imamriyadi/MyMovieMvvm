package tech.dreamcircle.mymoviemvvm.data.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import tech.dreamcircle.mymoviemvvm.data.remote.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository) :
    ViewModel() {
    val movies = repository.getNowPlayingMovies()
}