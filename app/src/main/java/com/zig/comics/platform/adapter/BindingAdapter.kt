package com.zig.comics.platform.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.zig.comics.core.extensions.loadImage
import com.zig.comics.core.extensions.visible

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:visible")
    fun visibilityView(view: View, visible: Boolean) {
        view.visible(visible)
    }

    @JvmStatic
    @BindingAdapter(value = ["app:image_url", "app:image_placeholder"], requireAll = false)
    fun setImageUrl(imageView: ImageView, url: String?, drawable: Drawable?) {
        imageView.loadImage(url) {
            drawable?.let {
                placeholder(it)
            }
        }
    }

}
