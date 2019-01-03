package com.example.dev.southbrmemes.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.dev.southbrmemes.BR

/**
 * Created by dev on 12/05/2018.
 */
class UserViewModel : BaseObservable(){

    @Bindable
    var login: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }

    @Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    var newPassword: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.newPassword)
        }

    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var cidade: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cidade)
        }

}