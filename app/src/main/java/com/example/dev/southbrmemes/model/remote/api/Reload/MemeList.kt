package com.example.dev.southbrmemes.model.remote.api.Reload

import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.model.remote.connect.URL
import com.example.dev.southbrmemes.model.remote.`interface`.IMeme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dev on 13/05/2018.
 */
object MemeList {
    fun getMeme(success: (note: Response<List<MemeViewModel>?>) -> Unit,
                failure: (throwable: Throwable) -> Unit) {

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        val call = retrofit.getMemes()
        call.enqueue(object : Callback<List<MemeViewModel>?> {
            override fun onResponse(call: Call<List<MemeViewModel>?>?, response: Response<List<MemeViewModel>?>?) {
                response?.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<List<MemeViewModel>?>?, t: Throwable?) {
                failure.invoke(t!!)
            }
        })
    }


    fun getMeme(memes: List<MemeViewModel>, success: (note: Response<List<MemeViewModel>?>) -> Unit,
                failure: (throwable: Throwable) -> Unit) {

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        val call = retrofit.getMemes(memes)

        call.enqueue(object : Callback<List<MemeViewModel>?> {
            override fun onResponse(call: Call<List<MemeViewModel>?>?, response: Response<List<MemeViewModel>?>?) {
                response?.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<List<MemeViewModel>?>?, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}