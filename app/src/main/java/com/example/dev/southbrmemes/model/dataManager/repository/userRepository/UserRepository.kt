package com.example.dev.southbrmemes.model.dataManager.repository.userRepository

import android.content.Context
import com.example.dev.southbrmemes.model.dataManager.dao.UserDAO
import com.example.dev.southbrmemes.model.dataManager.entity.UserEntity
import com.example.dev.southbrmemes.model.dataManager.repository.AbstractRepository
import com.google.gson.reflect.TypeToken


/**
 * Created by dev on 13/05/2018.
 */

class UserRepository(context: Context) : AbstractRepository<UserDAO, UserEntity>(context, object : TypeToken<UserDAO>() {}.type), IUserRepository {
    override fun buscarToken(): UserEntity? {
        return execute<UserEntity?> {
            _dao.buscarToken()
        }
    }

    override fun insert(type: UserEntity) {
        execute {
            _dao.insert(type)
        }
    }

    override fun update(type: UserEntity) {
        execute {
            _dao.update(type)
        }
    }

    override fun delete(type: UserEntity) {
        execute {
            _dao.delete(type)
        }
    }
}