package com.example.dev.southbrmemes.view.service.update

import android.app.Activity
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Update.UserUpdate
import com.example.dev.southbrmemes.model.utils.ObjectService

/**
 * Created by dev on 13/05/2018.
 */
class UserUpdateDomain(val activity: Activity) {

    fun update(user: UserViewModel) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "atualizando conta")

        UserUpdate.update(
                user = user,
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