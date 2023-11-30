package com.zig.comics.core.extensions

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zig.comics.R
import com.zig.comics.databinding.ProgressBinding

//region Create
fun Context.createDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}): AlertDialog {
    val builder = MaterialAlertDialogBuilder(this)
    builder.setPositiveMessage(android.R.string.ok)
    block(builder)
    return builder.create()
}

fun Context.createProgressDialog(): AlertDialog {
    return createDialog {
        val inflater = LayoutInflater.from(this@createProgressDialog)
        val inflate = DataBindingUtil.inflate<ProgressBinding>(inflater, R.layout.progress, null, false)

        setView(inflate.root)

        setPositiveButton(null, null)
        setCancelable(false)
    }
}
//endregion

//region Show
fun Fragment.showDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}) {
    requireContext().createDialog(block).show()
}

fun AppCompatActivity.showDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}) {
    createDialog(block).show()
}

fun Context.showDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}) {
    createDialog(block).show()
}
//endregion

//region Listeners
typealias Dialog = AlertDialog.Builder

fun Dialog.setPositiveMessage(message: String, listener: () -> Unit = {}) {
    setPositiveButton(message) { _, _ -> listener() }
}

fun Dialog.setPositiveMessage(@StringRes message: Int, listener: () -> Unit = {}) {
    setPositiveMessage(context.getString(message), listener)
}

fun Dialog.setPositiveListener(listener: () -> Unit) {
    setPositiveButton(android.R.string.ok) { _, _ -> listener() }
}

fun Dialog.setNegativeMessage(message: String, listener: () -> Unit = {}) {
    setNegativeButton(message) { _, _ -> listener() }
}

fun Dialog.setNegativeMessage(@StringRes message: Int, listener: () -> Unit = {}) {
    setNegativeMessage(context.getString(message), listener)
}
//endregion
