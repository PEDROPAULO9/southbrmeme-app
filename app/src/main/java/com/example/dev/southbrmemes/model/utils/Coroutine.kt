package com.example.dev.southbrmemes.model.utils

/**
 * Created by dev on 13/05/2018.
 */
class Coroutine{
    companion object {
         fun start(r: () -> Unit){
            val t = Thread(r);
            t.start()
            t.join()
        }
    }
}