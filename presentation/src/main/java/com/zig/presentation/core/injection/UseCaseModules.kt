package com.zig.presentation.core.injection

import com.zig.domain.comics.GetListComicsUseCase
import org.koin.dsl.module

private val comicsUseCases = module {
    factory { GetListComicsUseCase(get()) }
}

internal val useCaseModules = listOf(
    comicsUseCases
)
