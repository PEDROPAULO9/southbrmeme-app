package com.example.dev.southbrmemes.model.remote.`interface`

import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.viewmodel.ReturnViewModel
import retrofit2.Call
import retrofit2.http.*
import okhttp3.MultipartBody

/**
 * Created by dev on 13/05/2018.
 */
interface IMeme {

    @Multipart
    @POST("meme/insert/commit/{commit}")
    fun insert(@Path("commit") commit: String,
               @Part file: MultipartBody.Part,
               @Header("Authorization") token: String): Call<ReturnViewModel>

    @Multipart
    @POST("meme/update/id/{id}/commit/{commit}")
    fun update(@Path("id") id: Int,
               @Path("commit") commit: String,
               @Part file: MultipartBody.Part,
               @Header("Authorization") token: String): Call<ReturnViewModel>

    @DELETE("meme/delete/{id}")
    fun delete(@Path("id") id: Int, @Header("Authorization") token: String): Call<ReturnViewModel>

    @GET("meme/list")
    fun getMemes(): Call<List<MemeViewModel>>

    @POST("meme/list")
    fun getMemes(@Body memes: List<MemeViewModel>): Call<List<MemeViewModel>>

    @GET("meme/data")
    fun data(): Call<MemeViewModel>

    @GET("meme/download/{id}")
    fun download(@Path("id") id: Int): Call<ReturnViewModel>

    @GET("meme/mylist")
    fun getMyMemes(@Header("Authorization") token: String): Call<List<MemeViewModel>>
}