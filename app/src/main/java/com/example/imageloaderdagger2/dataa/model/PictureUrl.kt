package dev.ronnie.imageloaderdagger2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PictureUrl(var regular: String, var full: String) : Parcelable