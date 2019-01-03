package com.example.dev.southbrmemes.view.message

import android.app.Activity
import android.widget.Toast

/**
 * Created by dev on 12/05/2018.
 */

/**
 * Created by Kayque Rodrigues on 19/01/2018.
 */

object Message {

    fun messageReturn(texto: String?, activity: Activity?) {
        Toast.makeText(activity, texto, Toast.LENGTH_SHORT).show()
    }

}
