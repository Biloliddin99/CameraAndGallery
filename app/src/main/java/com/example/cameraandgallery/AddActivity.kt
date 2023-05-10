package com.example.cameraandgallery

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cameraandgallery.databinding.ActivityAddBinding
import com.example.cameraandgallery.models.MyImage
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            if (absolutePath!=""){
                if (binding.edtName.text.isNotBlank()){
                    MyDbHelper(this)
                        .addImage(MyImage(
                            binding.edtName.toString(),
                            absolutePath
                        ))
                    Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Nom berilmadi", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Rasm tanlanmadi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var absolutePath =""
    @SuppressLint("SimpleDateFormat")
    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it ?: return@registerForActivityResult
        binding.imageView.setImageURI(it)
        val inputStream = contentResolver.openInputStream(it)

        val name = SimpleDateFormat("yyyyMMdd_mm_ss").format(Date())

        val file = File(filesDir, "$name.jpg")
        val fileOutputStream = FileOutputStream(file)
        inputStream?.copyTo(fileOutputStream)
        inputStream?.close()
        fileOutputStream.close()

        absolutePath = file.absolutePath
    }



}