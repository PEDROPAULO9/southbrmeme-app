package com.example.dev.southbrmemes.view.service.update

import android.app.Activity
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Update.MemeUpdate
import com.example.dev.southbrmemes.model.utils.ObjectService
import java.io.File

/**
 * Created by dev on 13/05/2018.
 */
class MemeUpdateDomain(val activity: Activity, var file: File?) {

    fun update(meme: MemeViewModel) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "atualizando meme")

        MemeUpdate.update(
                meme = meme,
                file = file,
                activity = activity,
                success = { s ->
                    objectService.closeProgress()
                    if (s.code() == 200) {
                        s.body()?.let {
                            Message.messageReturn(it.answerText, activity)
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