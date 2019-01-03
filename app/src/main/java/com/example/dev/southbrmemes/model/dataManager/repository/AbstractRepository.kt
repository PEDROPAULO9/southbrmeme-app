package com.example.dev.southbrmemes.model.dataManager.repository

import android.content.Context
import com.example.dev.southbrmemes.model.utils.Coroutine
import com.example.dev.southbrmemes.model.dataManager.dao.IDao
import com.example.dev.southbrmemes.model.dataManager.dataBase.DataBase
import com.example.dev.southbrmemes.model.dataManager.dataBase.DataBaseSingleton
import java.lang.reflect.Type


/**
 * Created by dev on 13/05/2018.
 */
abstract class AbstractRepository<T, E>(context: Context, type: Type) where T : IDao<E> {
    protected var _dao: T

    private lateinit var c: Class<T>

    init {
        _dao = dbSet(context, type)
    }

    fun <U> dbSet(context: Context, type: Type): U where U : IDao<E> {
        return DataBase::class.java.methods.find { x -> x.returnType == type }?.invoke(
                DataBaseSingleton.getInstance(context)) as U
    }

    fun <U> execute(action: () -> U?): U? {
        var result: U? = null

        Coroutine.start {
            try {
                result = action()
            } catch (t: Throwable) {
                result = null
            }
        }
        return result
    }
}