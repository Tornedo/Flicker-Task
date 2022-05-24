package com.coding.flickertask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coding.flickertask.data.api.GetPhotoService
import com.coding.flickertask.data.model.PhotoItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1

class PhotoPagingSource(
    private val service: GetPhotoService,
    private val searchText: String,
    private val apiKey: String,
) : PagingSource<Int, PhotoItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val response = service.getPhotos(searchText = searchText, page = position, apiKey = apiKey)
            val photos = response.photos
            LoadResult.Page(
                data = photos.photo,
                prevKey = if (position == STARTING_PAGE) null else position,
                nextKey = if (photos.photo.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoItem>): Int? {
        TODO("Not yet implemented")
    }
}