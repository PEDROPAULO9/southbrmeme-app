package com.example.dev.southbrmemes.model.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.ImageView
import java.io.File

/**
 * Created by dev on 12/05/2018.
 */
class AccessPhoto{
    fun accessPhoto(requestCode: Int, resultCode: Int, data: Intent?, CAMERA: Int, imagemParaPoste: ImageView?, activity: Activity?): File? {
        if (requestCode == CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage = data.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            val c = activity?.contentResolver?.query(selectedImage!!, filePath, null, null, null)
            c!!.moveToFirst()
            val columnIndex = c.getColumnIndex(filePath[0])
            val picturePath = c.getString(columnIndex)
            c.close()
            val thumbnail = BitmapFactory.decodeFile(picturePath)
            val bitmapReduzido = Bitmap.createScaledBitmap(thumbnail, 1080, 600, true)
            imagemParaPoste?.setImageBitmap(bitmapReduzido)
            imagemParaPoste?.scaleType = ImageView.ScaleType.FIT_XY

            return File(picturePath)
        }

        return null
    }
}