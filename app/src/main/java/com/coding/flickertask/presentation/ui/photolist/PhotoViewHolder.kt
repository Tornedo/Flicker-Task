package com.coding.flickertask.presentation.ui.photolist

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.coding.flickertask.R
import com.facebook.drawee.view.SimpleDraweeView
import com.coding.flickertask.data.model.PhotoItem
import com.coding.flickertask.presentation.ui.photolist.callback.OnItemClickListener
import com.coding.flickertask.util.buildPhotoUri

    /*
        view holder for image item for the  RecyclerView
    */
class PhotoViewHolder(view: View, private val listener: OnItemClickListener) :
    RecyclerView.ViewHolder(view) {
    private val imageName: TextView = view.findViewById(R.id.imageTittle)
    private val imageView: SimpleDraweeView = view.findViewById(R.id.imageView)
    private var photo: PhotoItem? = null

    init {
        /*
            handle click on whole photo item view and open the image in browser
         */
        view.setOnClickListener {
            photo?.let { photo ->
                val url = buildPhotoUri(photo.farm,photo.server,photo.id,photo.secret)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bindPhotoItem(photo: PhotoItem?) {
        if (photo == null) {
            val resources = itemView.resources
            imageName.text = resources.getString(R.string.loading_text)
        } else {
            showImage(photo)
        }
    }

    private fun showImage(photo: PhotoItem) {
        this.photo = photo
        imageName.text = photo.title
        val url = buildPhotoUri(photo.farm,photo.server,photo.id,photo.secret)
        imageView.setImageURI(url)
    }

    companion object {
        fun createViewHolderItem(parent: ViewGroup, listener: OnItemClickListener): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false)
            return PhotoViewHolder(view, listener)
        }
    }
}
