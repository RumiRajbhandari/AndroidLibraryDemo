package com.example.demomodule.pref

import android.content.Context
import com.example.demomodule.Constants
import javax.inject.Inject

/**
 * Created by rumi on 6/12/18.
 */
class SharedPreferenceManager @Inject constructor(var context: Context){
    private val sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE)

    var userAccessToken: String
        get() = sharedPreferences.getString(Constants.PREF_ACCESS_TOKEN, "")
        set(accessToken) = sharedPreferences.edit().putString(Constants.PREF_ACCESS_TOKEN, accessToken).apply()


}