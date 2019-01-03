package com.example.dev.southbrmemes.model.remote.api.Data

import android.app.Activity
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
object UserData {
    fun data(activity: Activity, success: (note: Response<UserViewModel?>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IUser::class.java)

        val call = retrofit.data(URL.Bearer + SessionManager(activity).getPreferences(SessionManager.TOKEN))
        call.enqueue(object : Callback<UserViewModel?> {
            override fun onResponse(call: Call<UserViewModel?>?, response: Response<UserViewModel?>?) {
                response?.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<UserViewModel?>?, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}