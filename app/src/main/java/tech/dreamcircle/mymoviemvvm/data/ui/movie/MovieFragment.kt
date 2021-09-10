package tech.dreamcircle.mymoviemvvm.data.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.dreamcircle.mymoviemvvm.R
import tech.dreamcircle.mymoviemvvm.databinding.FragmentMovieBinding

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {
    private val viewModel by viewModels<MovieViewModel>()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieBinding.bind(view)
        val adapter = MovieAdapter()
        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
        }
        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }
}