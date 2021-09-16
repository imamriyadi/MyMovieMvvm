package tech.dreamcircle.mymoviemvvm.data.ui.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tech.dreamcircle.mymoviemvvm.R
import tech.dreamcircle.mymoviemvvm.data.local.FavoriteMovie
import tech.dreamcircle.mymoviemvvm.databinding.ItemMovieBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private lateinit var list: List<FavoriteMovie>
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setMovieItem(list: List<FavoriteMovie>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: FavoriteMovie)
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: FavoriteMovie) {
            with(binding) {
                Glide.with(itemView)
                    .load("${favoriteMovie.baseUrl}${favoriteMovie.posterPath}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(moviePoster)
                tvTitle.text = favoriteMovie.originalTitle
                binding.root.setOnClickListener {
                    onItemClickCallback?.onItemClick(favoriteMovie)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}