package tech.dreamcircle.mymoviemvvm.data.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tech.dreamcircle.mymoviemvvm.R
import tech.dreamcircle.mymoviemvvm.data.remote.ResultsItem
import tech.dreamcircle.mymoviemvvm.databinding.ItemMovieBinding

class MovieAdapter : PagingDataAdapter<ResultsItem, MovieAdapter.MovieViewBinding>(COMPARATOR) {
    inner class MovieViewBinding(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            with(binding) {
                Glide.with(itemView)
                    .load("${resultsItem.baseUrl}${resultsItem.posterPath}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(moviePoster)
                tvTitle.text = resultsItem.originalTitle
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewBinding {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewBinding(binding)
    }

    override fun onBindViewHolder(holder: MovieViewBinding, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem == newItem

        }
    }

}