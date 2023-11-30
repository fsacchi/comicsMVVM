package com.zig.presentation.core.injection

import com.zig.presentation.features.comics.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val comicsModules = module {
    viewModel { ComicsViewModel(get()) }
}

internal val viewModelModules = listOf(
    comicsModules
)
