package com.example.demomodule.data

import com.example.demomodule.data.local.user.UsersLocal
import com.example.demomodule.data.repository.UsersRepository
import com.example.demomodule.entity.User
import com.rosia.di.qualifiers.Local
import javax.inject.Inject

/**
 * Created by rumi on 6/13/18.
 */
class UsersRepositoryImpl @Inject constructor(@Local private val usersLocal: UsersLocal):UsersRepository {
    override fun saveUser(user: User) {
        usersLocal.saveUser(user)
    }

    override fun getUserDetail(): User {
        return usersLocal.getUserDetail()
    }
}