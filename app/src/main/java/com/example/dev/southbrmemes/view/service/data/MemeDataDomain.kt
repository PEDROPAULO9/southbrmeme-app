package com.example.dev.southbrmemes.view.service.data

import android.app.Activity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Data.MemeData
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.viewmodel.MemeViewModel


/**
 * Created by dev on 13/05/2018.
 */
class MemeDataDomain(val activity: Activity) {

    fun data(meme: MemeViewModel) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "Carregando meme")

        MemeData.getMeme(
                success = { s ->
                    objectService.closeProgress()
                    if (s.code() == 200) {
                        s.body()?.let {
                            meme.url = it.url
                            meme.name = it.name
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