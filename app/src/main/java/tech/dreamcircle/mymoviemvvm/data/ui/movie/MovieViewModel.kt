package tech.dreamcircle.mymoviemvvm.data.ui.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
    private val mvJob = Job()
    private val coroutineScope = CoroutineScope(mvJob+Dispatchers.IO)
    val movies = currentQuery.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getSearchMovies(query)
        } else {
            repository.getNowPlayingMovies().cachedIn(viewModelScope)
        }
    }

    fun searchMovie(query: String) {
        currentQuery.value = query
    }
}