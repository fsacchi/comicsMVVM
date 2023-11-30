package com.zig.comics.core.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun View.visible(show: Boolean) {
    if (show) visible() else gone()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.setOnDebouncedClickListener(delayInMilliSeconds: Long = 2000, action: () -> Unit) {
    val onDebouncedClickListener = OnDebouncedClickListener(delayInMilliSeconds, action)
    setOnClickListener(onDebouncedClickListener)
}

fun View.clickListener(closeKeyboard: Boolean = false, action: () -> Unit) {
    if (closeKeyboard) hideSoftKeyboard()
    setOnDebouncedClickListener { action() }
}

fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

class OnDebouncedClickListener(
    private val delayInMilliSeconds: Long,
    val action: () -> Unit
) : View.OnClickListener {
    var enable = true

    override fun onClick(view: View?) {
        if (enable) {
            enable = false
            view?.postDelayed({
                enable = true
            }, delayInMilliSeconds)
            action()
        }
    }
}

// region ImageView
fun ImageView.loadImage(uri: String?, block: RequestCreator.() -> Unit = {}) {
    uri?.let {
        if (it.isNotEmpty()) {
            Picasso.get()
                .load(uri)
                .apply { block(this) }
                .into(this)
        }
    }
}

// end region ImageView