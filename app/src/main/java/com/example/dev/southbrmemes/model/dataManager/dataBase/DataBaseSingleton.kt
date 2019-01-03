package com.example.dev.southbrmemes.model.dataManager.dataBase

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by dev on 13/05/2018.
 */
class DataBaseSingleton() {
    companion object {
        private var _db: DataBase? = null

        public fun getInstance(context: Context): DataBase {
            if (_db == null)
                _db = Room.databaseBuilder(context, DataBase::class.java, DataBase.DATABASE_NAME)
                        .build()

            return _db!!
        }

    }
}