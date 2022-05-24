package com.coding.flickertask.data.model

// The Topmost wrapper for the api response
data class PhotosSearchResponse(
    val photos: PhotosMetaData
)

data class PhotosMetaData(
    val page: Int,
    val total: Int,
    val photo: List<PhotoItem>
)

data class PhotoItem(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
