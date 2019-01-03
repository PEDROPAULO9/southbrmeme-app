package com.example.dev.southbrmemes.model.dataManager.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dev on 13/05/2018.
 */
@Entity(tableName = "USER")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ID")
        var id: Int = 0,

        @ColumnInfo(name = "TOKEN")
        var token: String? = ""
)