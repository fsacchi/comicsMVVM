package com.zig.comics.platform.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.zig.comics.BR
import com.zig.comics.core.extensions.setOnDebouncedClickListener

class DataBindingViewHolder<T, B : ViewDataBinding>(
    private val binding: B,
    var rootClick: (T) -> Unit = {}
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
        binding.root.setOnDebouncedClickListener {
            rootClick(item)
        }
    }

    fun getBinding() = binding
}
