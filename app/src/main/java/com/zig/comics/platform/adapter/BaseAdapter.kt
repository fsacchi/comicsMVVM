package com.zig.comics.platform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T, B : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBindingViewHolder<T, B>>(diffCallback) {

    abstract val layoutRes: Int
    var rootListener: (T) -> Unit = {}
    protected var adapterPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T, B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(layoutInflater, layoutRes, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T, B>, position: Int) {
        this.adapterPosition = holder.adapterPosition
        holder.bind(getItem(adapterPosition))
        holder.rootClick = rootListener

        holder.getBinding().bind(getItem(adapterPosition))
    }

    protected open fun B.bind(item: T) {}
}

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    override fun areContentsTheSame(oldItem: T, newItem: T) = true
}
