package com.example.demomodule.data.local.user

import com.example.demomodule.entity.Users
import com.example.demomodule.pref.SharedPreferencesManager
import javax.inject.Inject

/**
 * Created by rumi on 6/13/18.
 */
class UsersLocalImpl @Inject constructor(private var preferences: SharedPreferencesManager):UsersLocal {
    override fun saveUser(users: Users) {
        preferences.userAccessToken=users.token
    }

    override fun getUserDetail(): Users {
        return Users(preferences.userAccessToken)
    }

    override fun hasValidUser(): Boolean {
        return true
//        return preferences.
    }
}