package com.coding.flickertask.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.coding.flickertask.data.api.GetPhotoService
import com.coding.flickertask.data.model.PhotoItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import com.coding.flickertask.BuildConfig
import com.coding.flickertask.data.PhotoPagingSource

@ExperimentalCoroutinesApi
@FlowPreview

class PhotoRepository(private val service: GetPhotoService)  {

    companion object {
        private const val PAGE_SIZE = 5
    }

    /**
     * Fetch photos, expose them as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getPhotos(searchText: String): Flow<PagingData<PhotoItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { PhotoPagingSource(service, searchText, BuildConfig.API_KEY) }
        ).flow
   }
}
