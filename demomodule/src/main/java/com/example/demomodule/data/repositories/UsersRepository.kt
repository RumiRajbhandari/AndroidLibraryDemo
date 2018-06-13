package com.example.demomodule.data.repositories

import com.example.demomodule.entity.Users

interface UsersRepository {
    fun saveUser(users: Users)
    fun getUserDetail(): Users

}