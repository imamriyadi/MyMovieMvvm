package tech.dreamcircle.mymoviemvvm.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dates(
    @SerializedName("maximum")
    val maximum: String = "",
    @SerializedName("minimum")
    val minimum: String = ""
) : Parcelable