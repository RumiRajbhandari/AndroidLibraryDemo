package com.example.demomodule.data.local.user

import com.example.demomodule.entity.User

/**
 * Created by rumi on 6/13/18.
 */
interface UsersLocal {
    fun saveUser(user: User)
    fun getUserDetail(): User
    fun hasValidUser(): Boolean
}