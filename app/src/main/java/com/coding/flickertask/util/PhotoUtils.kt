package com.coding.flickertask.util

    /*
    This return the valid Url after contacting the mandatory fields to show the image
    */
fun buildPhotoUri(farm: Int, server: String, id: String, secret: String): String {
    return "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
}