package com.example.dev.southbrmemes.view.service.reload

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.view.recyclerView.Adapter.MyMemeAdapter
import com.example.dev.southbrmemes.model.remote.api.Reload.MyMemeList
import com.example.dev.southbrmemes.model.utils.ObjectService

/**
 * Created by dev on 13/05/2018.
 */
class MyMemeListDomain(val activity: Activity) {

    fun list(list: RecyclerView) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "Carregando memes")

        MyMemeList.getMeme(
                activity = activity,
                success = { s ->
                    objectService.closeProgress()

                    if (s.code() == 200) {
                        s.body()?.let {
                            list.setAdapter(MyMemeAdapter(activity, activity as FragmentActivity, it))
                            val layout = LinearLayoutManager(activity,
                                    LinearLayoutManager.VERTICAL, false)
                            list.setLayoutManager(layout)
                        }
                    } else {
                        Message.messageReturn("falha", activity)
                    }

                }, failure = { e ->
            objectService.closeProgress()
            Message.messageReturn("falha", activity)
        })
    }
}