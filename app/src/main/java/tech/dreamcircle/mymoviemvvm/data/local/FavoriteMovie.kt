package tech.dreamcircle.mymoviemvvm.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "favorite_movie")
@Parcelize
data class FavoriteMovie(
    var idMovie: String,
    var originalTitle: String,
    var overview: String,
    var posterPath: String
) : Serializable, Parcelable {
    @PrimaryKey (autoGenerate = true)
    var id : Int = 0
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}