package com.zig.data_remote.repository

import com.zig.data.model.ComicModel
import com.zig.data.repository.ComicsRepository
import com.zig.data_remote.BuildConfig
import com.zig.data_remote.core.extensions.exec
import com.zig.data_remote.services.ComicsServices
import kotlinx.coroutines.flow.Flow

class ComicsRemoteRepository(
    private val service: ComicsServices,
) : ComicsRepository {

    override fun getListComics(): Flow<ComicModel> = exec {
        service.getComics(BuildConfig.API_TS, BuildConfig.API_KEY, BuildConfig.API_HASH).data
    }


}
