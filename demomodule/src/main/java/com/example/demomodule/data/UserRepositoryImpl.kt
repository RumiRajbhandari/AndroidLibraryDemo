package com.example.demomodule.data

import com.example.demomodule.data.local.user.UserLocal
import com.example.demomodule.data.repository.UserRepository
import com.example.demomodule.entity.User
import com.rosia.di.qualifiers.Local
import javax.inject.Inject

/**
 * Created by rumi on 6/13/18.
 */
class UserRepositoryImpl @Inject constructor(@Local private val userLocal: UserLocal):UserRepository {
    override fun saveUser(user: User) {
        userLocal.saveUser(user)
    }

    override fun getUserDetail(): User {
        return userLocal.getUserDetail()
    }
}