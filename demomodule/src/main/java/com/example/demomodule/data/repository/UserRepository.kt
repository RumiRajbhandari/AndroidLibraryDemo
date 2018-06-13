package com.example.demomodule.data.repository

import com.example.demomodule.entity.User

interface UserRepository {
    fun saveUser(user: User)
    fun getUserDetail(): User

}