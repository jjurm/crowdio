package com.treecio.crowdio.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class PreferenceField<T>(
        val read: SharedPreferences.() -> T,
        val write: SharedPreferences.Editor.(value: T) -> SharedPreferences.Editor
) : ReadWriteProperty<Preferences, T> {

    override fun getValue(thisRef: Preferences, property: KProperty<*>): T = thisRef.prefs.read()
    override fun setValue(thisRef: Preferences, property: KProperty<*>, value: T) = thisRef.prefs.edit().write(value).apply()

}

class BooleanPreferenceField(val key: String, val defValue: Boolean) : PreferenceField<Boolean>(
        { getBoolean(key, defValue) },
        { putBoolean(key, it) }
)

class IntPreferenceField(val key: String, val defValue: Int) : PreferenceField<Int>(
        { getInt(key, defValue) },
        { putInt(key, it) }
)

class LongPreferenceField(val key: String, val defValue: Long) : PreferenceField<Long>(
        { getLong(key, defValue) },
        { putLong(key, it) }
)

class StringPreferenceField(val key: String, val defValue: String?) : PreferenceField<String?>(
        { getString(key, defValue) },
        { putString(key, it) }
)
