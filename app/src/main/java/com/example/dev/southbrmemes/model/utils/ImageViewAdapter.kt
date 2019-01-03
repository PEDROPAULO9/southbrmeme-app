package com.example.dev.southbrmemes.model.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageViewAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("android:url")
        fun ImageView.setImageUrl(url: String) {
            Picasso.get().load("${AWS.URL}${url}")
                    .into(this)
            this.setScaleType(ImageView.ScaleType.FIT_XY)
        }
    }
}