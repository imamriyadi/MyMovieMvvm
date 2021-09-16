package tech.dreamcircle.mymoviemvvm.data.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import tech.dreamcircle.mymoviemvvm.R
import tech.dreamcircle.mymoviemvvm.databinding.FragmentDetailsBinding
@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel:DetailsMovieModels by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            val movie = args.movie
            Glide.with(this@DetailsFragment)
                .load("${movie.baseUrl}${movie.posterPath}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        tvMovieDescription.isVisible = true
                        tvMovieTitle.isVisible = true
                        return false
                    }
                }).into(imgMoviePoster)
            var isChecked = false
            viewModel.checkMovie(movie.id.toString())
            viewModel.countFavorite.observe(viewLifecycleOwner, { count ->
                if (count > 0) {
                    toggleFavorite.isChecked = true
                    isChecked = true
                } else {
                    toggleFavorite.isChecked = false
                    isChecked = false
                }
            })
            tvMovieDescription.text = movie.overview
            tvMovieTitle.text = movie.originalTitle

            toggleFavorite.setOnClickListener {
                isChecked = !isChecked
                if (isChecked)
                    viewModel.addToFavorite(movie)
                else
                    viewModel.removeMovie(movie.id.toString())

                toggleFavorite.isChecked = isChecked
            }
        }
    }
}