package com.example.dev.southbrmemes.model.session

import android.content.Context
import com.orhanobut.hawk.Hawk

/**
 * Created by dev on 10/05/2018.
 */
class SessionManager(private val context: Context) {

    companion object {
        @JvmField val TOKEN = "TOKEMSOUTH";
    }

    fun setPreferences(key: String, value: String?) {
        value?.let {
            Hawk.put(key, value)
        }
    }

    fun getPreferences(key: String): String {
        Hawk.init(context).build()
        return Hawk.get(key, "")
    }

    fun cleanPreferences() {
        Hawk.init(context).build()
        Hawk.deleteAll()
    }
}