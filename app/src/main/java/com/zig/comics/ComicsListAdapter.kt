package com.zig.comics

import com.zig.comics.databinding.ItemListBinding
import com.zig.comics.platform.adapter.BaseAdapter
import com.zig.comics.platform.adapter.BaseDiffCallback
import com.zig.data.model.ResultModel

class ComicsListAdapter(
    override val layoutRes: Int = R.layout.item_list
) : BaseAdapter<ResultModel, ItemListBinding>(BaseDiffCallback())
