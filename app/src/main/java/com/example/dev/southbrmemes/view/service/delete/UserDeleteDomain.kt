package com.example.dev.southbrmemes.view.service.delete

import android.app.Activity
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Delete.UserDelete
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.activity.IndexActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserDeleteDomain(val activity: Activity) {

    fun delete() {

        val objectService = ObjectService()
        objectService.getDialog(activity, "deletando conta")

        UserDelete.delete(
                activity = activity,
                success = { s ->
                    objectService.closeProgress()

                    if (s.code() == 200) {
                        s.body()?.let {

                            Message.messageReturn(it.answerText, activity)
                            if (it.answer) {
                                SessionManager(context = activity).cleanPreferences()
                                ChangesActivity.changeActivityNoReturn(IndexActivity::class.java, activity)
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