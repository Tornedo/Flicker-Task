package com.coding.flickertask.presentation.ui.photolist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.coding.flickertask.data.model.PhotoItem
import com.coding.flickertask.presentation.ui.photolist.callback.OnItemClickListener
import com.coding.flickertask.presentation.ui.photolist.PhotoViewHolder

class PhotosItemAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<PhotoItem, PhotoViewHolder>(PHOTO_COMPARATOR) {

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.createViewHolderItem(parent, listener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { photo ->
            holder.bindPhotoItem(photo)
        }
    }
}
