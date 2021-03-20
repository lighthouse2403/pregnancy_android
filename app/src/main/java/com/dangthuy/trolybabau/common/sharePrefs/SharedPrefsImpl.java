package com.dangthuy.trolybabau.common.sharePrefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * SharedPrefs Utilities
 */
public class SharedPrefsImpl implements SharedPrefsApi {
    private SharedPreferences mSharedPreferences;
    private static SharedPrefsImpl instances;

    public static SharedPrefsImpl newInstance(String nameFilePref, Context context) {
        if (instances == null) {
            instances = new SharedPrefsImpl(context, nameFilePref);
        }
        return instances;
    }

    private SharedPrefsImpl(Context context, String nameFilePref) {
        this.mSharedPreferences = context.getSharedPreferences(nameFilePref, Context.MODE_PRIVATE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) mSharedPreferences.getString(key, "");
        } else if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, false));
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(mSharedPreferences.getFloat(key, 0));
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(mSharedPreferences.getInt(key, 0));
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(mSharedPreferences.getLong(key, 0));
        }
        return null;
    }

    @Override
    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        }
        editor.apply();
    }

    @Override
    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void removeAll() {
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public Set getAllKey() {
        return mSharedPreferences.getAll().keySet();
    }
}
