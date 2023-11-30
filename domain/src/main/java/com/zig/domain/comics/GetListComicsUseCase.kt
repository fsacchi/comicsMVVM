package com.zig.domain.comics

import com.zig.data.model.ComicModel
import com.zig.data.repository.ComicsRepository
import com.zig.domain.UseCase
import kotlinx.coroutines.flow.Flow

class GetListComicsUseCase(
    private val repository: ComicsRepository
) : UseCase.NoParam<ComicModel>() {

    override suspend fun execute(): Flow<ComicModel> {
        return repository.getListComics()
    }
}
