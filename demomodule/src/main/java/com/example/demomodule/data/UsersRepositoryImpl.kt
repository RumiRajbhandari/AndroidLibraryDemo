package com.example.demomodule.data

import com.example.demomodule.data.local.user.UsersLocal
import com.example.demomodule.data.repositories.UsersRepository
import com.example.demomodule.entity.Users
import com.rosia.di.qualifiers.Locals
import javax.inject.Inject

/**
 * Created by rumi on 6/13/18.
 */
class UsersRepositoryImpl @Inject constructor(@Locals private val usersLocal: UsersLocal):UsersRepository {
    override fun saveUser(users: Users) {
        usersLocal.saveUser(users)
    }

    override fun getUserDetail(): Users {
        return usersLocal.getUserDetail()
    }
}