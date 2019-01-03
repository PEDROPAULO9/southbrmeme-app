package com.example.dev.southbrmemes.model.dataManager.dao;

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

/**
 * Created by dev on 13/05/2018.
 */
@Dao
abstract class IDao<T>{

    @Insert
    abstract fun insert(type: T)

    @Update
    abstract fun update(type: T)

    @Delete
    abstract fun delete(type: T)
}