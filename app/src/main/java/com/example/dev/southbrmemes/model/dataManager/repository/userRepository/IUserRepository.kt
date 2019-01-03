package com.example.dev.southbrmemes.model.dataManager.repository.userRepository

import com.example.dev.southbrmemes.model.dataManager.entity.UserEntity
import com.example.dev.southbrmemes.model.dataManager.repository.IRepository


/**
 * Created by dev on 13/05/2018.
 */
interface IUserRepository : IRepository<UserEntity> {
    fun buscarToken(): UserEntity?
}