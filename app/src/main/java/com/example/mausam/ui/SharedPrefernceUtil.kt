package com.example.mausam.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferenceUtil(application: Application) {

        val TAG = SharedPreferenceUtil::class.java.getSimpleName()
        private val sharedPreferences: SharedPreferences=
            application.getSharedPreferences("", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        var itemList: String
            get() = sharedPreferences["itemList", ""]!!
            set(value) = sharedPreferences.set("itemList", value)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
        operator fun SharedPreferences.set(key: String, value: Any?) {
            when (value) {
                is String? -> edit({ it.putString(key, value) })
                is Int -> edit { it.putInt(key, value) }
                is Boolean -> edit({ it.putBoolean(key, value) })
                is Float -> edit({ it.putFloat(key, value) })
                is Long -> edit({ it.putLong(key, value) })
                else -> Log.e(TAG, "Setting shared pref failed for key: $key and value: $value ")
            }
        }
}