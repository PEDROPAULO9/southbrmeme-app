package com.example.dev.southbrmemes.model.dataManager.dataBase;

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dev.southbrmemes.model.dataManager.dao.UserDAO
import com.example.dev.southbrmemes.model.dataManager.entity.UserEntity

/**
 * Created by dev on 13/05/2018.
 */

@Database(entities = arrayOf(
        UserEntity::class),
        version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    companion object {
        public val DATABASE_NAME = "MEMESDATABASE"
    }

    abstract fun users(): UserDAO
}