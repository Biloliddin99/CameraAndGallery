package com.example.cameraandgallery.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraandgallery.databinding.ItemRvBinding
import com.example.cameraandgallery.models.MyImage

class ImageAdapter(val list: ArrayList<MyImage>) : RecyclerView.Adapter<ImageAdapter.Vh>() {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myImage: MyImage) {
            itemRvBinding.tvName.text = myImage.name
            itemRvBinding.imageView.setImageURI(Uri.parse(myImage.imagePath))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}
