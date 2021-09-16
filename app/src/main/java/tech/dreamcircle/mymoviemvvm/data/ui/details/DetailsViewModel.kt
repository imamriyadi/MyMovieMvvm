package tech.dreamcircle.mymoviemvvm.data.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.dreamcircle.mymoviemvvm.data.local.FavoriteMovie
import tech.dreamcircle.mymoviemvvm.data.local.FavoriteMovieRepository
import tech.dreamcircle.mymoviemvvm.data.remote.ResultsItem

class DetailsMovieModels @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
) : ViewModel() {

    var countFavorite = MutableLiveData<Int>()

    fun addToFavorite(movie: ResultsItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addFavoriteMovie(
                FavoriteMovie(
                    movie.id.toString(),
                    movie.originalTitle,
                    movie.overview,
                    movie.posterPath
                )
            )
        }
    }

    fun checkMovie(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            countFavorite.postValue(repository.checkMovie(id))
        }
    }

    fun removeMovie(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteMovie(id)
        }
    }
}