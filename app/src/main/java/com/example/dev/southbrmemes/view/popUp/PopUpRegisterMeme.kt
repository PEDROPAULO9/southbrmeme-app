package com.example.dev.southbrmemes.view.popUp

import android.app.Activity
import android.app.AlertDialog
import com.example.dev.southbrmemes.view.service.data.MemeDataDomain
import com.example.dev.southbrmemes.databinding.PopUpMenuMemeBinding
import com.example.dev.southbrmemes.viewmodel.MemeViewModel

/**
 * Created by dev on 13/05/2018.
 */
class PopUpRegisterMeme(val _activity: Activity) {

    val meme = MemeViewModel()

    fun creatPopUpMenu() {
        val memeDataDomain = MemeDataDomain(activity = _activity)
        val dialog = AlertDialog.Builder(_activity).create()
        val binding = PopUpMenuMemeBinding.inflate(_activity.layoutInflater)
        binding.meme = meme
        binding.memeDataDomain = memeDataDomain
        binding.alert = dialog
        dialog.setView(binding.getRoot())

        if (!_activity.isFinishing()) {
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

            MemeDataDomain(activity = _activity).data(meme)
        }

    }
}