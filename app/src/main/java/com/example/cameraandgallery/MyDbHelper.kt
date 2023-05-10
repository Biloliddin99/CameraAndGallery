package com.example.cameraandgallery

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION
import com.example.cameraandgallery.db.MyDbInterface
import com.example.cameraandgallery.models.MyImage

class MyDbHelper(val context: Context,):SQLiteOpenHelper(context, DB_NAME,null, VERSION),MyDbInterface {

    companion object{
        const val DB_NAME = "image_db"
        const val VERSION = 1
        const val TABLE_NAME = "image_table"
        const val ID = "id"
        const val NAME = "name"
        const val IMAGE = "image_link"

    }

    override fun onCreate(p0: SQLiteDatabase?){
        val query = "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique, $NAME text not null, $IMAGE text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addImage(myImage: MyImage) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME,myImage.name)
        contentValues.put(IMAGE,myImage.imagePath)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()

    }

    override fun getAllImages(): ArrayList<MyImage> {
        val list = ArrayList<MyImage>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    MyImage(
                    cursor.getString(1),
                    cursor.getString(2)
                )
                )
            }while (cursor.moveToNext())
        }
        return list
    }
}