package com.example.dev.southbrmemes.model.remote.api.Update

import android.app.Activity
import com.example.dev.southbrmemes.viewmodel.ReturnViewModel
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.remote.connect.URL
import com.example.dev.southbrmemes.model.remote.`interface`.IUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dev on 13/05/2018.
 */
object UserUpdate {

    fun update(user: UserViewModel, activity: Activity, success: (note: Response<ReturnViewModel?>) -> Unit,
               failure: (throwable: Throwable) -> Unit) {

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IUser::class.java)

        val call = retrofit.update(user,URL.Bearer + SessionManager(activity).getPreferences(SessionManager.TOKEN))
        call.enqueue(object : Callback<ReturnViewModel?> {
            override fun onResponse(call: Call<ReturnViewModel?>?, response: Response<ReturnViewModel?>?) {
                response?.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<ReturnViewModel?>?, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}