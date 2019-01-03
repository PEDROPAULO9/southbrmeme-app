package com.example.dev.southbrmemes.view.recyclerView.Adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dev.southbrmemes.model.utils.ImageBitmap
import com.example.dev.southbrmemes.model.utils.SavePhotoGallery
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import com.example.dev.southbrmemes.view.recyclerView.ViewHolder.MemeViewHolder
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.databinding.ListMemeBinding

/**
 * Created by dev on 08/03/2018.
 */
class MemeAdapter(val _context: Activity, val _itensMemes: ArrayList<MemeViewModel>) : RecyclerView.Adapter<MemeViewHolder>() {

    private var _savePhotoGallery: SavePhotoGallery

    init {
        this._savePhotoGallery = SavePhotoGallery()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val binding = DataBindingUtil.inflate<ListMemeBinding>(LayoutInflater.from(parent.context), R.layout.list_meme, parent, false)
        return MemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.meme = item
        holder.binding.image = ImageBitmap()
        holder.binding.activity = this._context
    }

    override fun getItemCount(): Int {
        return _itensMemes.size
    }

    fun getItem(position: Int): MemeViewModel {
        return _itensMemes.get(position)
    }

    fun getAdd(itensMemes: List<MemeViewModel>) {
        _itensMemes.addAll(itensMemes)
    }

    fun getMemes(): List<MemeViewModel> {
        return _itensMemes
    }

}