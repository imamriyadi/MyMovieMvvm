package tech.dreamcircle.mymoviemvvm.data.local

import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovie) =
        favoriteMovieDao.addFavorite(favoriteMovie)

    fun getFavoriteMovie() = favoriteMovieDao.getFavoriteMovie()

    suspend fun checkMovie(id: String): Int = favoriteMovieDao.checkMovie(id)

    suspend fun deleteMovie(id: String): Int = favoriteMovieDao.deleteMovie(id)
}