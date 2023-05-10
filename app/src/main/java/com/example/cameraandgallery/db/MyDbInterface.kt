package com.example.cameraandgallery.db

import com.example.cameraandgallery.models.MyImage

interface MyDbInterface {
    fun addImage(myImage: MyImage)
    fun getAllImages():ArrayList<MyImage>

}