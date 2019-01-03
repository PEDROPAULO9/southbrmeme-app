package com.example.dev.southbrmemes.model.dataManager.dao;

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dev.southbrmemes.model.dataManager.entity.UserEntity

/**
 * Created by dev on 13/05/2018.
 */
@Dao
abstract class UserDAO : IDao<UserEntity>() {

    @Query("SELECT * FROM USER WHERE ID = 1")
    abstract fun buscarToken(): UserEntity?
}