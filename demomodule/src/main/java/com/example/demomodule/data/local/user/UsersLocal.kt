package com.example.demomodule.data.local.user

import com.example.demomodule.entity.Users

/**
 * Created by rumi on 6/13/18.
 */
interface UsersLocal {
    fun saveUser(users: Users)
    fun getUserDetail(): Users
    fun hasValidUser(): Boolean
}