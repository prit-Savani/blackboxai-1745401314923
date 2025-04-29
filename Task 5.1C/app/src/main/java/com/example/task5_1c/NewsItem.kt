package com.example.task5_1c

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val title: String,
    val description: String,
    val imageUrl: String
) : Parcelable
