package com.example.dev.southbrmemes.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.dev.southbrmemes.BR


/**
 * Created by dev on 12/05/2018.
 */
 class MemeViewModel() : BaseObservable(){

    @Bindable
    var id: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var commit: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.commit)
        }

    @Bindable
    var url: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }

    @Bindable
    var dateCri: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateCri)
        }
}