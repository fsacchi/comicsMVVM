package com.zig.data.model

data class ComicModel(
    val results: List<ResultModel>
)

data class ResultModel(
    val title: String,
    val description: String?,
    val thumbnail: ThumbnailModel
) {
    fun urlImage() = "${thumbnail.path}.${thumbnail.extension}"
    fun subtitle() = description ?: "Sem descrição"
}

data class ThumbnailModel(
    val path: String,
    val extension: String,
)