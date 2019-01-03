package com.example.dev.southbrmemes.view.service.insert

import android.app.Activity
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Insert.MemeInsert
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.popUp.PopUpRegisterMeme
import java.io.File

/**
 * Created by dev on 13/05/2018.
 */
class MemeInsertDomain(val activity: Activity, var file: File?) {

    fun insert(meme: MemeViewModel) {

        if (file != null) {
            val objectService = ObjectService()
            objectService.getDialog(activity, "cadastrando meme")

            MemeInsert.insert(
                    file = file,
                    meme = meme,
                    activity = activity,
                    success = { s ->
                        objectService.closeProgress()

                        if (s.code() == 200) {
                            s.body()?.let {
                                Message.messageReturn(it.answerText, activity)
                                PopUpRegisterMeme(activity).creatPopUpMenu()
                            }
                        } else {
                            Message.messageReturn("falha", activity)
                        }

                    }, failure = { e ->
                objectService.closeProgress()
                Message.messageReturn("falha", activity)
            })
        } else {
            Message.messageReturn("É necessario uma imagem", activity)
        }
    }
}