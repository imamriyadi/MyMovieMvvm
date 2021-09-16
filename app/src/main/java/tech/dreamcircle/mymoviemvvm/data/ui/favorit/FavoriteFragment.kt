package tech.dreamcircle.mymoviemvvm.data.ui.favorit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.dreamcircle.mymoviemvvm.R
import tech.dreamcircle.mymoviemvvm.data.local.FavoriteMovie
import tech.dreamcircle.mymoviemvvm.data.remote.ResultsItem
import tech.dreamcircle.mymoviemvvm.databinding.FragmentFavoriteBinding

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)
        val adapter = FavoriteAdapter()
        viewModel.movie.observe(viewLifecycleOwner, {
            adapter.setMovieItem(it)
            binding.apply {
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = adapter
            }
        })
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClick(favoriteMovie: FavoriteMovie) {
                val movie = ResultsItem(
                    originalTitle = favoriteMovie.originalTitle,
                    overview = favoriteMovie.overview,
                    id = favoriteMovie.idMovie.toInt(),
                    posterPath = favoriteMovie.posterPath
                )
                val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
                findNavController().navigate(action)
            }
        })
    }
}