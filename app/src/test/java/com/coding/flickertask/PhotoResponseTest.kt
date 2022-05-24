package com.coding.flickertask

import com.coding.flickertask.data.model.PhotoItem
import com.coding.flickertask.data.model.PhotosMetaData
import com.coding.flickertask.data.model.PhotosSearchResponse
import org.junit.Test
import org.junit.Assert.assertEquals

class PhotoResponseTest {
    @Test
    fun test_Photo_Item() {
        val photoItem = PhotoItem("123", "ABC", "CBA", "XXX", 12, "hello")
        assertEquals("123", photoItem.id)
    }

    @Test
    fun test_Photo_Meta_Data() {
        val photosMetaData = PhotosMetaData(1,2, mutableListOf())
        assertEquals(1, photosMetaData.page)
    }

    @Test
    fun test_Photo_Search_Response() {
        val photosMetaData = PhotosMetaData(1,2, mutableListOf())
        val photosSearchResponse = PhotosSearchResponse(photosMetaData)
        assertEquals(2, photosSearchResponse.photos.total)
    }
}