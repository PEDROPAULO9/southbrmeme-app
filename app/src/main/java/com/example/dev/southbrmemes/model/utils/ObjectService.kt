package com.example.dev.southbrmemes.model.utils

import android.app.Activity
import android.app.ProgressDialog

/**
 * Created by dev on 13/05/2018.
 */
class ObjectService{

    private var dialog: ProgressDialog? = null

    fun getDialog(activity: Activity, texto: String): ProgressDialog {
        dialog = ProgressDialog(activity)
        dialog!!.setMessage(texto)
        dialog!!.setCancelable(true)
        if (!activity.isFinishing)
            dialog!!.show()
        return dialog!!
    }

    fun getDialogNocancel(activity: Activity, texto: String): ProgressDialog {
        dialog = ProgressDialog(activity)
        dialog!!.setMessage(texto)
        dialog!!.setCancelable(false)
        if (!activity.isFinishing)
            dialog!!.show()
        return dialog!!
    }

    fun closeProgress() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }
}