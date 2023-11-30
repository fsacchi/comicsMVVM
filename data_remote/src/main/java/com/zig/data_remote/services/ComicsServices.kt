package com.zig.data_remote.services

import com.zig.data.model.ComicModel
import com.zig.data_remote.core.model.RemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsServices {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : RemoteResponse<ComicModel>
}
