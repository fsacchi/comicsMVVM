package com.zig.presentation.features.comics

import androidx.lifecycle.viewModelScope
import com.zig.data.model.ComicModel
import com.zig.domain.comics.GetListComicsUseCase
import com.zig.presentation.core.extensions.LiveState
import com.zig.presentation.core.extensions.MutableState
import com.zig.presentation.core.extensions.observe
import com.zig.presentation.core.extensions.onError
import com.zig.presentation.core.extensions.onLoading
import com.zig.presentation.core.extensions.onSuccess
import com.zig.presentation.features.BaseViewModel
import kotlinx.coroutines.launch

class ComicsViewModel(
    private val getListComicsUseCase: GetListComicsUseCase
) : BaseViewModel() {

    private val _comicsState = MutableState<ComicModel>()
    val comicsState: LiveState<ComicModel> = _comicsState

    fun getListComics() {
        viewModelScope.launch {
            getListComicsUseCase()
                .observe(
                    onProgress = _comicsState.onLoading(),
                    onSuccess = _comicsState.onSuccess(),
                    onError = _comicsState.onError()
                )
        }
    }
}
