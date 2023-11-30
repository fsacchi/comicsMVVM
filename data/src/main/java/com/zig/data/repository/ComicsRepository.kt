package com.zig.data.repository

import com.zig.data.model.ComicModel
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    fun getListComics(): Flow<ComicModel>
}
