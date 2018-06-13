package com.example.demomodule.pref

import android.content.Context
import com.example.demomodule.Constant
import javax.inject.Inject

/**
 * Created by rumi on 6/12/18.
 */
class SharedPreferencesManager @Inject constructor(var context: Context){
    private val sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE)

    var userAccessToken: String
        get() = sharedPreferences.getString(Constant.PREF_ACCESS_TOKEN, "")
        set(accessToken) = sharedPreferences.edit().putString(Constant.PREF_ACCESS_TOKEN, accessToken).apply()


}