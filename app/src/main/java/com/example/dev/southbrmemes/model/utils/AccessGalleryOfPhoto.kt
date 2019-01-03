package com.example.dev.southbrmemes.model.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dev on 12/05/2018.
 */
class AccessGalleryOfPhoto {
    private val PERMISSAO_REQUEST = 2


    fun accessGallery(requestCode: Int, resultCode: Int, data: Intent?, imagemParaPoste: ImageView?, activity: Activity?): File? {
        if (resultCode == Activity.RESULT_OK && requestCode == 1 && data != null) {
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

    fun giraPhoto(file: File): File? {
        val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
        val matrix = Matrix()
        matrix.postRotate(90f)
        val bitmapReduzido = Bitmap.createBitmap(myBitmap, 0, 0,
                myBitmap.width, myBitmap.height,
                matrix, true)
        val bos = ByteArrayOutputStream()
        bitmapReduzido.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos)
        val bitmapdata = bos.toByteArray()
        val seuDiretorio = Environment.getExternalStorageDirectory().toString() + File.separator + filename()
        val imgToSdcard = File(seuDiretorio + File.separator + filename())
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(imgToSdcard, false)
            outputStream.write(bitmapdata, 0, bitmapdata.size)
            outputStream.flush()
            outputStream.close()
            return imgToSdcard
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun filename(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return "SouthBrMeme_$timeStamp.jpg"
    }

    fun permissionGallery(requestCode: Int, grantResults: IntArray, PERMISSAO_REQUEST: Int) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
            return
        }
    }

    fun callAccessGallery(activity: Activity?): Boolean {
        activity?.let {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    return false
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSAO_REQUEST)
                    return false
                }
            } else {
                return true
            }
        }
    }

    fun permissionAccessGallery(activity: Activity?, PERMISSAO_REQUEST: Int): Boolean {
        activity?.let {
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    return false
                } else {
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            PERMISSAO_REQUEST)
                    return false
                }
            }
            return true
        }
    }

}