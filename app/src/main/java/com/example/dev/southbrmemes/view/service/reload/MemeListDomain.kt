package com.example.dev.southbrmemes.view.service.reload

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.model.remote.api.Reload.MemeList
import com.example.dev.southbrmemes.model.utils.ObjectService
import com.example.dev.southbrmemes.view.recyclerView.Adapter.MemeAdapter

/**
 * Created by dev on 13/05/2018.
 */
class MemeListDomain(val activity: Activity) {

    fun list(list: RecyclerView) {

        val objectService = ObjectService()
        objectService.getDialog(activity, "Carregando meme")

        MemeList.getMeme(
                success = { s ->
                    objectService.closeProgress()

                    if (s.code() == 200) {
                        s.body()?.let {
                            val adapter = MemeAdapter(activity, it as ArrayList<MemeViewModel>)
                            list.setAdapter(adapter)
                            val layout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            list.setLayoutManager(layout)

                            list.setOnScrollListener(object : RecyclerView.OnScrollListener() {

                                var scrollDy = true

                                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                                    super.onScrollStateChanged(recyclerView, newState)

                                }

                                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                                    val scrollPosition = layout.findLastVisibleItemPosition()

                                    if (((adapter.itemCount - 1) - 5) == scrollPosition && scrollDy) {
                                        scrollDy = false
                                        refresh(memes = adapter.getMemes(),
                                                success = { lista ->
                                                    adapter.getAdd(lista)
                                                    adapter.notifyItemInserted(adapter.getItemCount())
                                                    scrollDy = true
                                                })
                                    }
                                }
                            })
                        }
                    } else {
                        Message.messageReturn("falha", activity)
                    }

                }, failure = { e ->
            objectService.closeProgress()
            Message.messageReturn("falha", activity)
        })
    }

    fun refresh(memes: List<MemeViewModel>, success: (note: ArrayList<MemeViewModel>) -> Unit) {
        MemeList.getMeme(
                memes = memes,
                success = { s ->
                    success.invoke(s.body() as ArrayList)
                }, failure = { e ->
            Message.messageReturn("falha", activity)
        })
    }
}
