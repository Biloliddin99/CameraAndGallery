package com.example.cameraandgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cameraandgallery.adapters.ImageAdapter
import com.example.cameraandgallery.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
                startActivity(Intent(this,AddActivity::class.java))

        }

    }

    override fun onResume() {
        super.onResume()
        myDbHelper = MyDbHelper(this)
        binding.itemRv.adapter = ImageAdapter(myDbHelper.getAllImages())
    }
    var currentImagePath = ""
    fun createTempFile(): File {
        val format = SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.getDefault()).format(Date())
        val externalFilesDir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES).apply {

        return File.createTempFile("JPEG_$format",".jpg")
        }
    }
}