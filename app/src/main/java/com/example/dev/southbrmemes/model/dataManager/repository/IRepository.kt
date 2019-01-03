package com.example.dev.southbrmemes.model.dataManager.repository

/**
 * Created by dev on 13/05/2018.
 */

interface IRepository<T> {
    fun insert(type: T)
    fun update(type: T)
    fun delete(type: T)
}