package com.example.demomodule.data.local.user

import com.example.demomodule.entity.User
import com.example.demomodule.pref.SharedPreferenceManager
import javax.inject.Inject

/**
 * Created by rumi on 6/13/18.
 */
class UserLocalImpl @Inject constructor(private var preferences: SharedPreferenceManager):UserLocal {
    override fun saveUser(user: User) {
        preferences.userAccessToken=user.token
    }

    override fun getUserDetail(): User {
        return User(preferences.userAccessToken)
    }

    override fun hasValidUser(): Boolean {
        return true
//        return preferences.
    }
}