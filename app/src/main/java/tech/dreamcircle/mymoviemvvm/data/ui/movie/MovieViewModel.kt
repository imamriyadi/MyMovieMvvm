package tech.dreamcircle.mymoviemvvm.data.ui.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import tech.dreamcircle.mymoviemvvm.data.remote.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted state: SavedStateHandle
) :
    ViewModel() {
    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getSearchMovies(query)
        } else {
            repository.getNowPlayingMovies()
        }
    }

    fun searchMovie(query: String) {
        currentQuery.value = query
    }
}