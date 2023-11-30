package com.zig.comics

import androidx.appcompat.app.AlertDialog
import com.zig.comics.core.extensions.clickListener
import com.zig.comics.core.extensions.createProgressDialog
import com.zig.comics.databinding.ActivityMainBinding
import com.zig.comics.platform.BaseActivity
import com.zig.data.model.ComicModel
import com.zig.data.model.ResultModel
import com.zig.presentation.core.state.SimpleState
import com.zig.presentation.features.comics.ComicsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var dialog: AlertDialog
    private val viewModel: ComicsViewModel by viewModel()
    private val adapter by lazy { ComicsListAdapter() }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun init() {
        dialog = createProgressDialog()
        viewModel.getListComics()
        viewModel.comicsState.observe(this, ::handle)
        setListeners()
    }

    private fun setListeners() {
        binding.emptyView.btnAction.clickListener{
            viewModel.getListComics()
        }

        binding.swipe.setOnRefreshListener {
            viewModel.getListComics()
        }
    }

    private fun setAdapter(items: List<ResultModel>) {
        dialog.dismiss()
        binding.rvComics.adapter = adapter
        adapter.submitList(items)
        binding.swipe.isRefreshing = false
        binding.showError = false
    }

    private fun setError(throwable: Throwable? = null) {
        dialog.dismiss()
        binding.showError = true
        throwable?.let {
            binding.errorMessage = it.message ?: getString(R.string.msg_error_default)
        }
    }

    private fun handle(state: SimpleState<ComicModel>) {
        when (state) {
            SimpleState.Loading -> if (!binding.swipe.isRefreshing) dialog.show()
            is SimpleState.Loaded -> {
                setAdapter(state.data.results)
            }
            is SimpleState.Failed -> {
                setError(state.throwable)
            }
            else -> {
                setError()
            }
        }
    }
}
