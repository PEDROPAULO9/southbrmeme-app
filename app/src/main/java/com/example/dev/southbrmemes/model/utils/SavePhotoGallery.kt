package com.example.dev.southbrmemes.model.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dev on 13/05/2018.
 */
class SavePhotoGallery {

    private var fileName: String? = null

    fun getFileName(): String? {
        return fileName
    }

    @Throws(IOException::class)
    fun createFile(): File {
        this.fileName = filename()
        val pasta = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES + "/SouthBrMeme")
        pasta.mkdir()
        return File(pasta.path + "/"
                + fileName)
    }

    fun filename(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return "SouthBrMeme_$timeStamp.jpg"
    }

    fun recordPhoto(requestCode: Int, resultCode: Int, Tirar_Foto: Int, activity: Activity?, imageView: ImageView?, arquivoFoto: File?) {
        if (requestCode == Tirar_Foto && resultCode == android.app.Activity.RESULT_OK) {
            activity?.sendBroadcast(Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(arquivoFoto))
            )
            if (arquivoFoto != null && imageView != null)
                ajustaFoto(activity, arquivoFoto, imageView)
        }
    }

    fun displayImage(imagem: ImageView, arquivoFoto: File, camera: Int) {
        val targetW = imagem.width
        val targetH = imagem.height
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(arquivoFoto.absolutePath, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight
        val scaleFactor = Math.min(photoW / targetW, photoH / targetH)
        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor
        val bitmap = BitmapFactory.decodeFile(arquivoFoto.absolutePath, bmOptions)
        imagem.setImageBitmap(bitmap)
        imagem.scaleType = ImageView.ScaleType.FIT_XY
    }


    fun permissionAccess(activity: Activity?, PERMISSAO_REQUEST: Int): Boolean {
        activity?.let {
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    return false
                } else {
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            PERMISSAO_REQUEST)
                    return false
                }
            }
            return true
        }
    }

    fun ajustaFoto(activity: Activity?, file: File?, image: ImageView) {
        if (file != null) {
            try {
                activity?.contentResolver?.notifyChange(PhotoProvider.getPhotoUri(file.absoluteFile), null)
                val cr = activity?.contentResolver
                var bitmap: Bitmap? = null
                var w = 0
                var h = 0
                var mtx = Matrix()


                bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, Uri.fromFile(file))

                w = bitmap!!.width
                h = bitmap.height
                mtx = Matrix()

                val exif = ExifInterface(file.absolutePath)

                val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

                when (orientation) {
                    3 -> mtx.postRotate(180f)
                    6 -> mtx.postRotate(90f)
                    8 -> mtx.postRotate(270f)
                    else -> mtx.postRotate(0f)
                }

                val rotatedBmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true)
                val bmpd = BitmapDrawable(rotatedBmp)

                val lateral = 256

                val out = FileOutputStream(file.absolutePath)

                val bmp = bmpd.bitmap

                var idx: Int? = 1

                w = bmp.width
                h = bmp.height

                if (w >= h) {
                    idx = w / lateral
                } else {
                    idx = h / lateral
                }

                w = w / idx
                h = h / idx

                val bmpReduzido = Bitmap.createScaledBitmap(bmp, w, h, true)

                bmpReduzido.compress(Bitmap.CompressFormat.PNG, 90, out)
                image.setImageBitmap(bmpReduzido)
                image.scaleType = ImageView.ScaleType.FIT_XY
            } catch (e: FileNotFoundException) {
            } catch (ignored: IOException) {
            }

        }
    }
}