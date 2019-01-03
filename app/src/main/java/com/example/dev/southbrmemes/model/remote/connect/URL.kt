package com.example.dev.southbrmemes.model.remote.connect

/**
 * Created by dev on 13/05/2018.
 */
class URL {
    companion object {
        private val URL_SERVECE = "http://192.168.1.31:8080"
        private val NAME_SERVICE = "/southbrmeme/"
        val WEB_SERVICE = URL_SERVECE + NAME_SERVICE

        val Bearer = "Bearer "
    }
}