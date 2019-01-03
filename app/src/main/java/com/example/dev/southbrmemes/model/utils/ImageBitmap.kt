package com.example.dev.southbrmemes.model.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore.Images
import com.example.dev.southbrmemes.view.message.Message
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.lang.Exception


/**
 * Created by dev on 25/05/2018.
 */
class ImageBitmap {

    fun getDownload(url: String, activity: Activity) {
        Picasso.get()
                .load("${AWS.URL}${url}")
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {

                        Message.messageReturn("O download foi iniciado", activity = activity)

                        val load = ObjectService()
                        load.getDialogNocancel(activity, "realizando download")
                        activity.runOnUiThread {
                            try {
                                val save = SavePhotoGallery()

                                val bos = ByteArrayOutputStream()
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                                val bitmapdata = bos.toByteArray()

                                val imgfile = save.createFile()

                                val outputStream = FileOutputStream(imgfile, false)
                                outputStream.write(bitmapdata, 0, bitmapdata.size)
                                outputStream.flush()
                                outputStream.close()

                                Message.messageReturn("Download realizado com sucesso", activity = activity)
                            } catch (ex: Exception) {
                                Message.messageReturn("Houve algum erro, Tente novamente.", activity = activity)
                            }
                        }

                        load.closeProgress()
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Message.messageReturn("Houve algum erro, Tente novamente.", activity = activity)
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable) {

                    }
                })
    }



    fun getSend(url: String, activity: Activity) {
        Picasso.get()
                .load("${AWS.URL}${url}")
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {

                        try {
                            val load = ObjectService()
                            load.getDialogNocancel(activity, "carregando imagem")

                            val bytes = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
                            val path = Images.Media.insertImage(activity.getContentResolver(), bitmap, "South Br Memes", null)
                            val uri = Uri.parse(path)

                            load.closeProgress()

                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.putExtra(Intent.EXTRA_STREAM, uri)
                            sendIntent.type = "image/png"
                            activity.startActivity(Intent.createChooser(sendIntent, "South Br Memes"))
                        } catch (ex: Exception) {
                            Message.messageReturn("Houve algum erro, Tente novamente.", activity = activity)
                        }
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Message.messageReturn("Houve algum erro, Tente novamente.", activity = activity)
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable) {

                    }
                })
    }
}