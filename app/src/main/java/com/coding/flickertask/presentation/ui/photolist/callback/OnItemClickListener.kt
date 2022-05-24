package com.coding.flickertask.presentation.ui.photolist.callback

import com.coding.flickertask.data.model.PhotoItem

interface OnItemClickListener {
    fun onItemClick(item: PhotoItem)
}