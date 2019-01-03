package com.example.dev.southbrmemes.model.remote.`interface`

import com.example.dev.southbrmemes.viewmodel.ReturnViewModel
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dev on 13/05/2018.
 */
interface IUser{

    @GET("user/data")
    fun data(@Header("Authorization") token: String): Call<UserViewModel>

    @POST("user/login")
    fun login(@Body user: UserViewModel): Call<ReturnViewModel>

    @POST("user/insert")
    fun insert(@Body user: UserViewModel): Call<ReturnViewModel>

    @PUT("user/update")
    fun update(@Body user: UserViewModel, @Header("Authorization") token: String): Call<ReturnViewModel>

    @DELETE("user/delete")
    fun delete(@Header("Authorization") token: String): Call<ReturnViewModel>

}