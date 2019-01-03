package com.example.dev.southbrmemes.view.service.auth

import android.app.Activity
import android.widget.Toast
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import com.example.dev.southbrmemes.view.router.ChangesActivity
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Auth.UserLogin
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.activity.LoggedActivity

/**
 * Created by dev on 13/05/2018.
 */
class UserLoginDomain(val activity: Activity) {

    fun login(user : UserViewModel) {
        val objectService = ObjectService()
        objectService.getDialog(activity, "efetuando login")

        UserLogin.login(
                user = user,
                success = { s ->
                    objectService.closeProgress()

                    if (s.code() == 200) {
                        s.body()?.let {

                            Message.messageReturn(it.answerText, activity)

                            if (it.answer) {
                                Message.messageReturn(it.answerText, activity)
                                SessionManager(context = activity).setPreferences(SessionManager.TOKEN,it.token)

                                Toast.makeText(activity, "toque no icone do aplicativo, para ver os memes um por um", Toast.LENGTH_LONG).show()
                                ChangesActivity.changeActivityNoReturn(LoggedActivity::class.java, activity)
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