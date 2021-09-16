package tech.dreamcircle.mymoviemvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun addFavorite(favoriteMovie: FavoriteMovie)

    @Query("SELECT COUNT(*) FROM favorite_movie WHERE idMovie = :id")
    suspend fun checkMovie(id: String): Int

    @Query("DELETE FROM favorite_movie WHERE idMovie = :id")
    suspend fun deleteMovie(id: String): Int

}