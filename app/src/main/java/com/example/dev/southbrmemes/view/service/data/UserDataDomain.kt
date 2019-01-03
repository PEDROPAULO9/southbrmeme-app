package com.example.dev.southbrmemes.view.service.data

import android.app.Activity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Data.UserData
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.viewmodel.UserViewModel

/**
 * Created by dev on 13/05/2018.
 */
class UserDataDomain(val activity: Activity) {

    fun data(user: UserViewModel) {
        val objectService = ObjectService()
        objectService.getDialog(activity, "buscando dados")

        UserData.data(
                activity = activity,
                success = { s ->
                    objectService.closeProgress()
                    if (s.code() == 200) {
                        s.body()?.let {
                            user.name = it.name
                            user.login = it.login
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