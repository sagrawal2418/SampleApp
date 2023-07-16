package com.sample.practiceapp.view

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.practiceapp.R
import com.sample.practiceapp.model.Photo

object ImageUtils {

    fun loadImage(context: Context, imageUrl: Photo, imageView: AppCompatImageView) {
        // Advanced usage with RequestOptions
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
            .error(R.drawable.ic_launcher_background) // Error image if the request fails

        Glide.with(context)
            .load(imageUrl.url)
            .apply(requestOptions)
            .into(imageView)
    }
}