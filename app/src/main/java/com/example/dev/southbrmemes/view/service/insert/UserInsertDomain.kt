package com.example.dev.southbrmemes.view.service.insert

import android.app.Activity
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Insert.UserInsert
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.activity.LoggedActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserInsertDomain(val activity: Activity) {

    fun insert(user: UserViewModel) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "cadastrando usuario")

        UserInsert.insert(
                user = user,
                success = { s ->
                    objectService.closeProgress()
                    if (s.code() == 200) {
                        s.body()?.let {
                            Message.messageReturn(it.answerText, activity)
                            if (it.answer) {
                                SessionManager(context = activity).setPreferences(SessionManager.TOKEN,it.token)

                                ChangesActivity.changeActivity(LoggedActivity::class.java, activity)
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