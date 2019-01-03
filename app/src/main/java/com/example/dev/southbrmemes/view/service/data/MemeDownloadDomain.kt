package com.example.dev.southbrmemes.view.service.data

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import com.example.dev.southbrmemes.model.utils.SavePhotoGallery
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Data.MemeDownload
import com.example.dev.southbrmemes.model.utils.ObjectService
import java.io.FileOutputStream
import java.util.*

/**
 * Created by dev on 13/05/2018.
 */
class MemeDownloadDomain(val activity: Activity) {

    @TargetApi(Build.VERSION_CODES.O)
    fun download(id: Int) {
        val objectService = ObjectService()
        objectService.getDialog(activity, "realizando Download")

        MemeDownload.getDownload(
                id = id,
                success = { s ->
                    objectService.closeProgress()

                    if (s.code() == 200) {
                        s.body()?.let {
                            if (it.image != null) {

                                activity.runOnUiThread {

                                    val image = Base64.getDecoder().decode(it.image)
                                    val save = SavePhotoGallery()
                                    val img = save.createFile()
                                    val outputStream = FileOutputStream(img, false)
                                    outputStream.write(image, 0, image!!.size)
                                    outputStream.flush()
                                    outputStream.close()

                                    Message.messageReturn("download realizado com sucesso.", activity)
                                }

                            } else {
                                Message.messageReturn(it.answerText, activity)
                            }
                        }
                    } else {
                        Message.messageReturn("falha", activity)
                    }

                }, failure = { e ->
            objectService.closeProgress()
            Message.messageReturn("falha", activity)
        })
    }
}