package com.example.dev.southbrmemes.model.remote.api.Update

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.viewmodel.ReturnViewModel
import com.example.dev.southbrmemes.model.session.SessionManager
import com.example.dev.southbrmemes.model.remote.connect.URL
import com.example.dev.southbrmemes.model.remote.`interface`.IMeme
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * Created by dev on 13/05/2018.
 */
object MemeUpdate {
    fun update(meme: MemeViewModel, file: File?, activity: Activity, success: (note: Response<ReturnViewModel?>) -> Unit,
               failure: (throwable: Throwable) -> Unit) {

        val retrofit = Retrofit.Builder()
                .baseUrl(URL.WEB_SERVICE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IMeme::class.java)

        var requestFile: RequestBody? = null

        var bytes = ByteArrayOutputStream()

        file?.let {
            val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
            bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, bytes)
        }

        requestFile = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                bytes.toByteArray()
        )


        val call = retrofit.update(meme.id,
                meme.commit ?: "",
                MultipartBody.Part.createFormData("image", file?.getName(), requestFile),
                URL.Bearer + SessionManager(activity).getPreferences(SessionManager.TOKEN))
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