package com.coding.flickertask.data.api

import com.coding.flickertask.data.model.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPhotoService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&safe_search=1")
    suspend fun getPhotos(
        @Query("text") searchText: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): PhotosSearchResponse
}
