package tech.dreamcircle.mymoviemvvm.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<ResultsItem>?,
    @SerializedName("total_results")
    val totalResults: Int = 0
) : Parcelable {
    val baseUrl: String get() = "https://image.tmbd.org/t/p/w500"
}