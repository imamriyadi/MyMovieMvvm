package tech.dreamcircle.mymoviemvvm.data.ui.favorit

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import tech.dreamcircle.mymoviemvvm.data.local.FavoriteMovieRepository

class FavoriteViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    val movie = repository.getFavoriteMovie()

}