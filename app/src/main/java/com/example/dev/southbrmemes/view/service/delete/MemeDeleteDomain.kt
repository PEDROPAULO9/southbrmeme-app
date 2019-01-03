package com.example.dev.southbrmemes.view.service.delete

import android.app.Activity
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Delete.MemeDelete
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.activity.IndexActivity
import com.example.dev.southbrmemes.viewmodel.MemeViewModel

/**
 * Created by dev on 13/05/2018.
 */
class MemeDeleteDomain(val activity: Activity) {

    fun delete(meme: MemeViewModel) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "deletando meme")

        MemeDelete.delete(
                id = meme.id,
                activity = activity,
                success = { s ->
                    objectService.closeProgress()
                    if (s.code() == 200) {
                        s.body()?.let {
                            Message.messageReturn(it.answerText, activity)
                            if (it.answer)
                                ChangesActivity.changeActivityNoReturn(IndexActivity::class.java, activity)

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