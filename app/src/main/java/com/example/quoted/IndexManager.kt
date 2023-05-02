package com.example.quoted

import android.annotation.SuppressLint
import android.content.Context
import androidx.preference.PreferenceManager

@SuppressLint("RestrictedApi")
class IndexManager(context: Context) : PreferenceManager(context) {
    companion object {
        const val INDEX_KEY = "Last Saved Index"
    }
    fun saveIndex(index: Int) {
        val sharedPrefs = getDefaultSharedPreferences(context).edit()
        sharedPrefs.putInt(INDEX_KEY, index)
        sharedPrefs.apply()
    }

    fun readSavedIndex() : Int {
        val sharedPrefs = getDefaultSharedPreferences(context)
        return sharedPrefs.getInt(INDEX_KEY, 0)
    }
}