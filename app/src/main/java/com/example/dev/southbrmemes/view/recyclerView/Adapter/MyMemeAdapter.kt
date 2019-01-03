package com.example.dev.southbrmemes.view.recyclerView.Adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.view.recyclerView.ViewHolder.MyMemeViewHolder
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.databinding.ListMyMemeBinding
import com.example.dev.southbrmemes.view.fragment.EditMemeFragment

/**
 * Created by dev on 08/03/2018.
 */
class MyMemeAdapter(val _context: Activity, val _fragmentActivity: FragmentActivity, val _itensMemes: List<MemeViewModel>) : RecyclerView.Adapter<MyMemeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMemeViewHolder {
        val binding = DataBindingUtil.inflate<ListMyMemeBinding>(LayoutInflater.from(parent.context), R.layout.list_my_meme, parent, false)
        return MyMemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMemeViewHolder, position: Int) {

        val item = getItem(position)
        holder.binding.meme = item

        holder.binding.fabEditMyMeme.setOnClickListener { v ->
            val arg = Bundle()
            arg.putInt("id", item.id)
            arg.putString("url", item.url)
            arg.putString("commit", item.commit)

            val editMemeFragment = EditMemeFragment.getInstance()
            editMemeFragment.arguments = arg

            chengefragment(editMemeFragment, R.id.flLogged)
        }
    }

    override fun getItemCount(): Int {
        return _itensMemes.size
    }

    fun getItem(position: Int): MemeViewModel {
        return _itensMemes.get(position)
    }

    fun chengefragment(fragment: Fragment, id: Int) {
        _fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment, null)
                .addToBackStack("Fragment")
                .commit()
    }
}